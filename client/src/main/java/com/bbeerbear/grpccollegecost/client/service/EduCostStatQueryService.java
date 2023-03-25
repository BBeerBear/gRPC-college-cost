package com.bbeerbear.grpccollegecost.client.service;

import com.bbeerbear.grpccollegecost.client.dto.EduStatQueryResultOne;
import com.bbeerbear.grpccollegecost.common.Expense;
import com.bbeerbear.grpccollegecost.common.Length;
import com.bbeerbear.grpccollegecost.common.Type;
import com.bbeerbear.grpccollegecost.server.EduCostStatQueryOneGrpc;
import com.bbeerbear.grpccollegecost.server.EduCostStatQueryOneRequest;
import com.bbeerbear.grpccollegecost.server.EduCostStatQueryOneResponse;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EduCostStatQueryService {
    @GrpcClient("edu-cost-stat-query-service")
    private EduCostStatQueryOneGrpc.EduCostStatQueryOneBlockingStub eduCostStatQueryOneBlockingStub;

    public List<EduStatQueryResultOne> eduStatQueryOne(int year, String state, String typeString, String lengthString, String expenseString){
        Type type = null;
        Length length = null;
        Expense expense = null;
        switch (typeString){
            case "Private" -> type = Type.PRIVATE;
            case "Public In-State" -> type = Type.PUBLIC_IN_STATE;
            case "Public Out-of-State" -> type = Type.PUBLIC_OUT_OF_STATE;
        }
        switch (lengthString) {
            case "2-year" -> length = Length._2_YEAR;
            case "4-year" -> length = Length._4_YEAR;
        }
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
}
