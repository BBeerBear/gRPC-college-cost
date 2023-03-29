package com.bbeerbear.grpccollegecost.client.service;

import com.bbeerbear.grpcclooegecost.server.*;
import com.bbeerbear.grpccollegecost.client.dto.EduStatQueryResultFive;
import com.bbeerbear.grpccollegecost.client.dto.EduStatQueryResultFour;
import com.bbeerbear.grpccollegecost.client.dto.EduStatQueryResultOne;
import com.bbeerbear.grpccollegecost.client.dto.EduStatQueryResultTwo;
import com.bbeerbear.grpccollegecost.client.util.EnumTransferUtil;
import com.bbeerbear.grpccollegecost.common.Expense;
import com.bbeerbear.grpccollegecost.common.Length;
import com.bbeerbear.grpccollegecost.common.Type;
import com.bbeerbear.grpccollegecost.server.*;
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
    public List<EduStatQueryResultOne> eduStatQueryOne(int year, String state, String typeString, String lengthString, String expenseString){
        Type type = null;
        Length length = null;
        Expense expense = null;
        if(typeString != null)
            switch (typeString){
                case "Private" -> type = Type.PRIVATE;
                case "Public In-State" -> type = Type.PUBLIC_IN_STATE;
                case "Public Out-of-State" -> type = Type.PUBLIC_OUT_OF_STATE;
            }
        if(lengthString != null)
            switch (lengthString) {
                case "2-year" -> length = Length._2_YEAR;
                case "4-year" -> length = Length._4_YEAR;
            }
        if(expenseString!=null)
            switch (expenseString) {
                case "Fees/Tuition" -> expense = Expense.FEES_TUITION;
                case "Room/Board" -> expense = Expense.ROOM_BOARD;
            }
        EduCostStatQueryOneRequest eduCostStatQueryOneRequest = EduCostStatQueryOneRequest.newBuilder()
                .setYear(year).setState(state).setType(type)
                .setLength(length).setExpense(expense)
                .build();
        EduCostStatQueryOneResponse eduCostStatQueryOneResponse = this.eduCostStatQueryOneBlockingStub.getEduCostStatQueryOne(eduCostStatQueryOneRequest);

        return eduCostStatQueryOneResponse.getEduCostStatQueryOneValueList()
                .stream()
                .map(eduCostStatQueryOneValue -> new EduStatQueryResultOne(eduCostStatQueryOneValue.getId(),
                        eduCostStatQueryOneValue.getValue()))
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
                .map(eduCostQueryTwoState -> new EduStatQueryResultTwo(eduCostQueryTwoState.getState(),
                        eduCostQueryTwoState.getOverallExpense()))
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
                .map(eduCostQueryTwoState -> new EduStatQueryResultTwo(eduCostQueryTwoState.getState(),
                        eduCostQueryTwoState.getOverallExpense()))
                .collect(Collectors.toList());
    }

    public List<EduStatQueryResultFour> eduStatQueryFour(int pastYears, String type, String length){
        EduCostStatQueryFourRequest build = EduCostStatQueryFourRequest.newBuilder()
                .setPastYear(EnumTransferUtil.pastYearsTransfer(pastYears))
                .setLength(EnumTransferUtil.lengthTransfer(length))
                .setType(EnumTransferUtil.typeTransfer(type))
                .build();

        EduCostStatQueryFourResponse eduCostStatQueryFourResponse = this.eduCostStatQueryFourServiceBlockingStub.getEduCostQueryFour(build);
//        System.out.println(eduCostStatQueryFourResponse.getEduCostStatQueryFourStateList());
        return eduCostStatQueryFourResponse.getEduCostStatQueryFourStateList()
                .stream()
                .map(eduCostStatQueryFourState -> new EduStatQueryResultFour(eduCostStatQueryFourState.getState(),
                        eduCostStatQueryFourState.getGrowthRate()))
                .collect(Collectors.toList());
    }

    public List<EduStatQueryResultFive> eduStatQueryFive(int year, String type, String length){
        EduCostStatQueryFiveRequest build = EduCostStatQueryFiveRequest.newBuilder()
                .setYear(year)
                .setLength(EnumTransferUtil.lengthTransfer(length))
                .setType(EnumTransferUtil.typeTransfer(type))
                .build();
        EduCostStatQueryFiveResponse eduCostStatQueryFiveResponse = this.eduCostStatQueryFiveServiceBlockingStub.getEduCostQueryFive(build);
//        System.out.println(eduCostStatQueryFiveResponse.getEduCostStatQueryFiveRegionList());
        return eduCostStatQueryFiveResponse.getEduCostStatQueryFiveRegionList()
                .stream()
                .map(eduCostStatQueryFiveRegion -> new EduStatQueryResultFive(eduCostStatQueryFiveRegion.getRegion(),
                        eduCostStatQueryFiveRegion.getAverageOverallExpense()))
                .collect(Collectors.toList());
    }

}
