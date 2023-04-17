package com.bbeerbear.grpccollegecost.aggregator.service;

import com.bbeerbear.grpccollegecost.query.*;
import com.bbeerbear.grpccollegecost.aggregator.dto.EduStatQueryResultFive;
import com.bbeerbear.grpccollegecost.aggregator.dto.EduStatQueryResultFour;
import com.bbeerbear.grpccollegecost.aggregator.dto.EduStatQueryResultOne;
import com.bbeerbear.grpccollegecost.aggregator.dto.EduStatQueryResultTwo;
import com.bbeerbear.grpccollegecost.aggregator.util.EnumTransferUtil;
import com.bbeerbear.grpccollegecost.query.*;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class EduCostStatQueryService {
    @GrpcClient("edu-cost-stat-query-service")
    private EduCostStatQueryOneGrpc.EduCostStatQueryOneBlockingStub eduCostStatQueryOneBlockingStub;
    @GrpcClient("edu-cost-stat-query-service")
    private EduCostStatQueryTwoGrpc.EduCostStatQueryTwoBlockingStub eduCostStatQueryTwoBlockingStub;
    @GrpcClient("edu-cost-stat-query-service")
    private EduCostStatQueryThreeGrpc.EduCostStatQueryThreeBlockingStub eduCostStatQueryThreeBlockingStub;
    @GrpcClient("edu-cost-stat-query-service")
    private EduCostStatQueryFourServiceGrpc.EduCostStatQueryFourServiceBlockingStub eduCostStatQueryFourServiceBlockingStub;
    @GrpcClient("edu-cost-stat-query-service")
    private EduCostStatQueryFiveServiceGrpc.EduCostStatQueryFiveServiceBlockingStub eduCostStatQueryFiveServiceBlockingStub;
    public List<EduStatQueryResultOne> eduStatQueryOne(int year, String state, String typeString,
                                                       String lengthString, String expenseString){
        EduCostStatQueryOneRequest eduCostStatQueryOneRequest = EduCostStatQueryOneRequest.newBuilder()
                .setYear(year)
                .setState(state)
                .setType(EnumTransferUtil.typeTransfer(typeString))
                .setLength(EnumTransferUtil.lengthTransfer(lengthString))
                .setExpense(EnumTransferUtil.expenseTransfer(expenseString))
                .build();
        EduCostStatQueryOneResponse eduCostStatQueryOneResponse = this.eduCostStatQueryOneBlockingStub
                .getEduCostStatQueryOne(eduCostStatQueryOneRequest);

        return eduCostStatQueryOneResponse.getEduCostStatQueryOneValueList()
                .stream()
                .map(eduCostStatQueryOneValue ->
                        new EduStatQueryResultOne(
                            eduCostStatQueryOneValue.getId(),
                            eduCostStatQueryOneValue.getValue(),
                            year,
                            state,
                            typeString,
                            lengthString,
                            expenseString
                        ))
                .collect(Collectors.toList());
    }

    public List<EduStatQueryResultTwo> eduStatQueryTwo(Integer year, String type, String length){
        EduCostStatQueryTwoRequest request = EduCostStatQueryTwoRequest.newBuilder()
                .setYear(year)
                .setType(EnumTransferUtil.typeTransfer(type))
                .setLength(EnumTransferUtil.lengthTransfer(length))
                .build();
        EduCostStatQueryTwoResponse eduCostStatQueryTwoResponse = this.eduCostStatQueryTwoBlockingStub
                .getEduCostStatQueryTwo(request);
        return eduCostStatQueryTwoResponse.getEduCostStatQueryTwoStateList()
                .stream()
                .map(eduCostQueryTwoState -> new EduStatQueryResultTwo(
                        eduCostQueryTwoState.getState(),
                        eduCostQueryTwoState.getOverallExpense(),
                        year,
                        type,
                        length))
                .collect(Collectors.toList());
    }
    public List<EduStatQueryResultTwo> eduStatQueryThree(int year, String type, String length){
        EduCostStatQueryThreeRequest request = EduCostStatQueryThreeRequest.newBuilder()
                .setYear(year)
                .setType(EnumTransferUtil.typeTransfer(type))
                .setLength(EnumTransferUtil.lengthTransfer(length))
                .build();
        EduCostStatQueryThreeResponse eduCostStatQueryThreeResponse = this.eduCostStatQueryThreeBlockingStub
                .getEduCostStatQueryThree(request);
//        System.out.println(eduCostStatQueryThreeResponse.getEduCostStatQueryThreeStateList());
        return eduCostStatQueryThreeResponse.getEduCostStatQueryThreeStateList()
                .stream()
                .map(eduCostQueryThreeState -> new EduStatQueryResultTwo(
                        eduCostQueryThreeState.getState(),
                        eduCostQueryThreeState.getOverallExpense(),
                        year,
                        type,
                        length))
                .collect(Collectors.toList());
    }

    public List<EduStatQueryResultFour> eduStatQueryFour(int pastYears, String type, String length){
        EduCostStatQueryFourRequest build = EduCostStatQueryFourRequest.newBuilder()
                .setPastYear(EnumTransferUtil.pastYearsTransfer(pastYears))
                .setLength(EnumTransferUtil.lengthTransfer(length))
                .setType(EnumTransferUtil.typeTransfer(type))
                .build();

        EduCostStatQueryFourResponse eduCostStatQueryFourResponse = this.eduCostStatQueryFourServiceBlockingStub
                .getEduCostQueryFour(build);
//        System.out.println(eduCostStatQueryFourResponse.getEduCostStatQueryFourStateList());
        return eduCostStatQueryFourResponse.getEduCostStatQueryFourStateList()
                .stream()
                .map(eduCostStatQueryFourState -> new EduStatQueryResultFour(
                        eduCostStatQueryFourState.getState(),
                        eduCostStatQueryFourState.getGrowthRate(),
                        pastYears,
                        type,
                        length))
                .collect(Collectors.toList());
    }

    public List<EduStatQueryResultFive> eduStatQueryFive(int year, String type, String length){
        EduCostStatQueryFiveRequest build = EduCostStatQueryFiveRequest.newBuilder()
                .setYear(year)
                .setLength(EnumTransferUtil.lengthTransfer(length))
                .setType(EnumTransferUtil.typeTransfer(type))
                .build();
        EduCostStatQueryFiveResponse eduCostStatQueryFiveResponse = this.eduCostStatQueryFiveServiceBlockingStub
                .getEduCostQueryFive(build);
//        System.out.println(eduCostStatQueryFiveResponse.getEduCostStatQueryFiveRegionList());
        return eduCostStatQueryFiveResponse.getEduCostStatQueryFiveRegionList()
                .stream()
                .map(eduCostStatQueryFiveRegion -> new EduStatQueryResultFive(
                        eduCostStatQueryFiveRegion.getRegion(),
                        eduCostStatQueryFiveRegion.getAverageOverallExpense(),
                        year,
                        type,
                        length))
                .collect(Collectors.toList());
    }
}
