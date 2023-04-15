package com.bbeerbear.grpccollegecost.educoststatquery.service;

import com.bbeerbear.grpcclooegecost.server.EduCostStatQueryFourRequest;
import com.bbeerbear.grpcclooegecost.server.EduCostStatQueryFourResponse;
import com.bbeerbear.grpcclooegecost.server.EduCostStatQueryFourServiceGrpc;
import com.bbeerbear.grpcclooegecost.server.EduCostStatQueryFourState;
import com.bbeerbear.grpccollegecost.educoststatquery.entity.EduCostStatQueryFour;
import com.bbeerbear.grpccollegecost.educoststatquery.repository.EduCostStatQueryFourRepository;
import com.bbeerbear.grpccollegecost.educoststatquery.utils.EnumTransferUtil;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

/**
 * Query the top 5 states of the highest growth rate of overall expense given a range of past years, one year, three years and five years (using the latest year as the base) , type and length;
 * and save the query as a document in a collection named EduCostStatQueryFour.
 */
@GrpcService
public class EduCostStatQueryFourService extends EduCostStatQueryFourServiceGrpc.EduCostStatQueryFourServiceImplBase {
    private final EduCostStatQueryFourRepository eduCostStatQueryFourRepository;
    private final MongoTemplate mongoTemplate;

    public EduCostStatQueryFourService(EduCostStatQueryFourRepository eduCostStatQueryFourRepository,
                                       MongoTemplate mongoTemplate) {
        this.eduCostStatQueryFourRepository = eduCostStatQueryFourRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void getEduCostQueryFour(EduCostStatQueryFourRequest request,
                                    StreamObserver<EduCostStatQueryFourResponse> responseObserver) {
        int pastYears = EnumTransferUtil.pastYearsTransfer(request.getPastYear());
        Aggregation agg = Aggregation.newAggregation(
                match(new Criteria("year").in(2021 - pastYears, 2021)
                        .and("type").is(EnumTransferUtil.typeTransfer(request.getType()))
                        .and("length").is(EnumTransferUtil.lengthTransfer(request.getLength()))), // need to improve
                group("state", "year" ).sum("value").as("overallExpense"),
                sort(Sort.Direction.ASC, "_id.year"),
                group("_id.state").push("overallExpense").as("overallExpenses"),
                project().and("_id").as("state")
                         .and(ArithmeticOperators.Multiply
                                 .valueOf(
                                         ArithmeticOperators.Divide
                                                 .valueOf(
                                                         ArithmeticOperators.Subtract
                                                                 .valueOf(
                                                                         ArrayOperators
                                                                                 .arrayOf("overallExpenses")
                                                                                 .elementAt(1)

                                                                 ).subtract(
                                                                         ArrayOperators
                                                                                 .arrayOf("overallExpenses")
                                                                                 .elementAt(0)
                                                                 )
                                                 ).divideBy(ArrayOperators
                                                         .arrayOf("overallExpenses")
                                                         .elementAt(0))
                                 ).multiplyBy(100)).as("growthRate"),
                sort(Sort.Direction.DESC, "growthRate"),
                limit(5),
                project().andExclude("_id")
                        .and("state").as("state")
                        .and("growthRate").as("growthRate")
        );
        AggregationResults<EduCostStatQueryFour> results = mongoTemplate.aggregate(agg, "eduCostStat",
                EduCostStatQueryFour.class);
        List<EduCostStatQueryFour> top5States = results.getMappedResults();
//        System.out.println(mappedResults);

        // Return to client
        List<EduCostStatQueryFourState> eduCostStatQueryFourStates = top5States
                .stream()
                .map(eduCostStatItem -> EduCostStatQueryFourState.newBuilder()
                        .setState(eduCostStatItem.getState())
                        .setGrowthRate(eduCostStatItem.getGrowthRate())
                        .build())
                .collect(Collectors.toList());
        responseObserver.onNext(EduCostStatQueryFourResponse.newBuilder()
                .addAllEduCostStatQueryFourState(eduCostStatQueryFourStates)
                .build());
        responseObserver.onCompleted();

        // Save to MongoDB
        this.eduCostStatQueryFourRepository.deleteAll();
        this.eduCostStatQueryFourRepository.saveAll(top5States);
    }
}
