package com.bbeerbear.grpccollegecost.query.service;

import com.bbeerbear.grpccollegecost.query.EduCostStatQueryFiveRegion;
import com.bbeerbear.grpccollegecost.query.EduCostStatQueryFiveRequest;
import com.bbeerbear.grpccollegecost.query.EduCostStatQueryFiveResponse;
import com.bbeerbear.grpccollegecost.query.EduCostStatQueryFiveServiceGrpc;
import com.bbeerbear.grpccollegecost.query.entity.EduCostStatQueryFive;
import com.bbeerbear.grpccollegecost.query.repository.EduCostStatQueryFiveRepository;
import com.bbeerbear.grpccollegecost.query.utils.EnumTransferUtil;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.aggregation.ConditionalOperators.when;
import static org.springframework.data.mongodb.core.aggregation.ConditionalOperators.switchCases;

/**
 * Aggregate region‘s average overall expense for a given year, type and length;
 * and save the query as a document in a collection named EduCostStatQueryFive.
 */
@GrpcService
public class EduCostStatQueryFiveService extends EduCostStatQueryFiveServiceGrpc.EduCostStatQueryFiveServiceImplBase {
    private final EduCostStatQueryFiveRepository eduCostStatQueryFiveRepository;
    private final MongoTemplate mongoTemplate;

    public EduCostStatQueryFiveService(EduCostStatQueryFiveRepository eduCostStatQueryFiveRepository,
                                       MongoTemplate mongoTemplate) {
        this.eduCostStatQueryFiveRepository = eduCostStatQueryFiveRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void getEduCostQueryFive(EduCostStatQueryFiveRequest request,
                                    StreamObserver<EduCostStatQueryFiveResponse> responseObserver) {
        // Aggregate region‘s average overall expense for a given year, type and length
        AggregationExpression condExpression = ConditionalOperators.when(
                Criteria.where("_id").in("Alaska", "Arizona", "California", "Colorado", "Hawaii", "Idaho",
                        "Montana", "Nevada", "New Mexico", "Oregon", "Utah", "Washington", "Wyoming"))
                .then("West")
                .otherwiseValueOf(when(Criteria.where("_id").in("Illinois", "Indiana", "Iowa", "Kansas",
                        "Michigan", "Minnesota", "Missouri", "Nebraska",
                        "North Dakota", "Ohio", "South Dakota", "Wisconsin"))
                        .then("Midwest")
                        .otherwiseValueOf(when(Criteria.where("_id").in("Delaware", "Connecticut", "Maine",
                                "Massachusetts", "New Hampshire", "New Jersey", "New York", "Pennsylvania",
                                "Rhode Island", "Vermont"))
                                .then("Northeast")
                                .otherwiseValueOf(when(Criteria.where("_id").in("Maryland", "Alabama",
                                        "Arkansas", "Florida", "Georgia", "Kentucky", "Louisiana", "Mississippi",
                                        "North Carolina", "South Carolina", "Tennessee", "Virginia", "West Virginia"))
                                        .then("Southeast")
                                        .otherwiseValueOf(when(Criteria.where("_id").in("Arizona",
                                                "New Mexico", "Oklahoma", "Texas"))
                                                .then("Southwest")
                                                .otherwise("Mid-Atlantic")))));

        Aggregation agg = newAggregation(
                match(Criteria.where("year").is(request.getYear())
                        .and("type").is(EnumTransferUtil.typeTransfer(request.getType()))
                        .and("length").is(EnumTransferUtil.lengthTransfer(request.getLength()))),
                group("state").sum("value").as("overallExpense"),
                project().andExclude("_id")
                        .and(condExpression).as("region")
                        .and("overallExpense").as("overallExpense"),
                group("region").avg("overallExpense").as("averageOverallExpense"),
                project().andExclude("_id")
                        .and("_id").as("region")
                        .and("averageOverallExpense").as("averageOverallExpense")
        );
        AggregationResults<EduCostStatQueryFive> eduCostStat = mongoTemplate.aggregate(agg, "eduCostStat",
                EduCostStatQueryFive.class);
        List<EduCostStatQueryFive> avgResults = eduCostStat.getMappedResults();
//        System.out.println(avgResults);

        // Return to Client
        List<EduCostStatQueryFiveRegion> eduCostStatQueryFiveRegions = avgResults
                .stream()
                .map(eduCostStatItem -> EduCostStatQueryFiveRegion.newBuilder()
                        .setRegion(eduCostStatItem.getRegion())
                        .setAverageOverallExpense(eduCostStatItem.getAverageOverallExpense())
                        .build())
                .collect(Collectors.toList());
        responseObserver.onNext(EduCostStatQueryFiveResponse.newBuilder()
                .addAllEduCostStatQueryFiveRegion(eduCostStatQueryFiveRegions)
                .build());
        responseObserver.onCompleted();

        // Save to MongoDB
        eduCostStatQueryFiveRepository.deleteAll();
        eduCostStatQueryFiveRepository.saveAll(avgResults);
    }
}
