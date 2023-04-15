package com.bbeerbear.grpccollegecost.query.service;

import com.bbeerbear.grpccollegecost.query.EduCostStatQueryTwoGrpc;
import com.bbeerbear.grpccollegecost.query.EduCostStatQueryTwoRequest;
import com.bbeerbear.grpccollegecost.query.EduCostStatQueryTwoResponse;
import com.bbeerbear.grpccollegecost.query.EduCostStatQueryTwoState;
import com.bbeerbear.grpccollegecost.query.entity.EduCostStatQueryTwo;
import com.bbeerbear.grpccollegecost.query.repository.EduCostStatQueryTwoRepository;
import com.bbeerbear.grpccollegecost.query.utils.EnumTransferUtil;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

/**
 * Query the top 5 most expensive states (with overall expense) given a year, type, length
 */
@GrpcService
public class EduCostStatQueryTwoService extends EduCostStatQueryTwoGrpc.EduCostStatQueryTwoImplBase {
    private final EduCostStatQueryTwoRepository eduCostStatQueryTwoRepository;
    private final MongoTemplate mongoTemplate;

    public EduCostStatQueryTwoService(EduCostStatQueryTwoRepository eduCostStatQueryTwoRepository, MongoTemplate mongoTemplate) {
        this.eduCostStatQueryTwoRepository = eduCostStatQueryTwoRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void getEduCostStatQueryTwo(EduCostStatQueryTwoRequest request, StreamObserver<EduCostStatQueryTwoResponse> responseObserver) {

        Aggregation agg = Aggregation.newAggregation(
                match(Criteria.where("year").is(request.getYear())
                        .and("length").is(EnumTransferUtil.lengthTransfer(request.getLength()))
                        .and("type").is(EnumTransferUtil.typeTransfer(request.getType()))
                ),
                group("state").sum("value").as("overallExpense"),
                sort(Sort.Direction.DESC, "overallExpense"),
                limit(5),
                project().andExpression("_id").as("state")
                        .andExpression("overallExpense").as("overallExpense")
        );
        AggregationResults<EduCostStatQueryTwo> result = mongoTemplate.aggregate(agg, "eduCostStat", EduCostStatQueryTwo.class);
        List<EduCostStatQueryTwo> top5States = result.getMappedResults();
//        System.out.println(top5States);

        // send back to client
        List<EduCostStatQueryTwoState> eduCostStatQueryTwoStates = top5States
                .stream()
                .map(eduCostStatItem -> EduCostStatQueryTwoState.newBuilder()
                        .setState(eduCostStatItem.getState())
                        .setOverallExpense(eduCostStatItem.getOverallExpense())
                        .build())
                .collect(Collectors.toList());

        responseObserver.onNext(EduCostStatQueryTwoResponse.newBuilder()
                .addAllEduCostStatQueryTwoState(eduCostStatQueryTwoStates)
                .build());
        responseObserver.onCompleted();

        // save to mongodb
        eduCostStatQueryTwoRepository.deleteAll();
        eduCostStatQueryTwoRepository.saveAll(top5States);
    }
}
