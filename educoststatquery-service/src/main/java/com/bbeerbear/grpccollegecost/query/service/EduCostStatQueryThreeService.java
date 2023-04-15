package com.bbeerbear.grpccollegecost.query.service;

import com.bbeerbear.grpccollegecost.query.EduCostStatQueryThreeGrpc;
import com.bbeerbear.grpccollegecost.query.EduCostStatQueryThreeRequest;
import com.bbeerbear.grpccollegecost.query.EduCostStatQueryThreeResponse;
import com.bbeerbear.grpccollegecost.query.EduCostStatQueryThreeState;
import com.bbeerbear.grpccollegecost.query.entity.EduCostStatQueryThree;
import com.bbeerbear.grpccollegecost.query.repository.EduCostStatQueryThreeRepository;
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
 * Query the top 5 most economic states (with overall expense) given a year, type, length;
 * and save the query as a document in a collection named EduCostStatQueryThree.
 */
@GrpcService
public class EduCostStatQueryThreeService extends EduCostStatQueryThreeGrpc.EduCostStatQueryThreeImplBase {
    private final EduCostStatQueryThreeRepository eduCostStatQueryThreeRepository;
    private final MongoTemplate mongoTemplate;

    public EduCostStatQueryThreeService(EduCostStatQueryThreeRepository eduCostStatQueryThreeRepository, MongoTemplate mongoTemplate) {
        this.eduCostStatQueryThreeRepository = eduCostStatQueryThreeRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void getEduCostStatQueryThree(EduCostStatQueryThreeRequest request, StreamObserver<EduCostStatQueryThreeResponse> responseObserver) {
        // Query the top 5 most economic states (with overall expense) given a year, type, length
        Aggregation agg = Aggregation.newAggregation(
                match(Criteria.where("year").is(request.getYear())
                        .and("length").is(EnumTransferUtil.lengthTransfer(request.getLength()))
                        .and("type").is(EnumTransferUtil.typeTransfer(request.getType()))),
                group("state").sum("value").as("overallExpense"),
                sort(Sort.Direction.ASC, "overallExpense"),
                limit(5),
                project().andExpression("_id").as("state")
                        .andExpression("overallExpense").as("overallExpense")
        );
        AggregationResults<EduCostStatQueryThree> result = mongoTemplate.aggregate(agg, "eduCostStat", EduCostStatQueryThree.class);
        List<EduCostStatQueryThree> top5States = result.getMappedResults();
//        System.out.println(top5States);

        // send back to client
        List<EduCostStatQueryThreeState> eduCostStatQueryThreeStates = top5States
                .stream()
                .map(eduCostStatItem -> EduCostStatQueryThreeState.newBuilder()
                        .setState(eduCostStatItem.getState())
                        .setOverallExpense(eduCostStatItem.getOverallExpense())
                        .build())
                .collect(Collectors.toList());

        responseObserver.onNext(EduCostStatQueryThreeResponse.newBuilder()
                .addAllEduCostStatQueryThreeState(eduCostStatQueryThreeStates)
                .build());
        responseObserver.onCompleted();

        // save to mongodb
        eduCostStatQueryThreeRepository.deleteAll();
        eduCostStatQueryThreeRepository.saveAll(top5States);
    }


}
