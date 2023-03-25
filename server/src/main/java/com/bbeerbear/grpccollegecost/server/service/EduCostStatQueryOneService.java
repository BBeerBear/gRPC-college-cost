package com.bbeerbear.grpccollegecost.server.service;

import com.bbeerbear.grpccollegecost.server.EduCostStatQueryOneGrpc;
import com.bbeerbear.grpccollegecost.server.EduCostStatQueryOneRequest;
import com.bbeerbear.grpccollegecost.server.EduCostStatQueryOneResponse;
import com.bbeerbear.grpccollegecost.server.EduCostStatQueryOneValue;
import com.bbeerbear.grpccollegecost.server.entity.EduCostStat;
import com.bbeerbear.grpccollegecost.server.entity.EduCostStatQueryOne;
import com.bbeerbear.grpccollegecost.server.repository.EduCostStatQueryOneRepository;
import com.bbeerbear.grpccollegecost.server.repository.EduCostStatRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class EduCostStatQueryOneService extends EduCostStatQueryOneGrpc.EduCostStatQueryOneImplBase {
    @Autowired
    private EduCostStatRepository eduCostStatRepository;
    @Autowired
    private EduCostStatQueryOneRepository eduCostStatQueryOneRepository;

    @Override
    public void getEduCostStatQueryOne(EduCostStatQueryOneRequest request, StreamObserver<EduCostStatQueryOneResponse> responseObserver) {
        String type = null,length = null,expense = null;
        switch (request.getType()){
            case PRIVATE -> type = "Private";
            case PUBLIC_IN_STATE -> type = "Public In-State";
            case PUBLIC_OUT_OF_STATE -> type = "Public Out-Of-State";
        }
        switch (request.getLength()) {
            case _2_YEAR -> length = "2-year";
            case _4_YEAR -> length = "4-year";
        }
        switch (request.getExpense()) {
            case FEES_TUITION -> expense = "Fees/Tuition";
            case ROOM_BOARD -> expense = "Room/Board";
        }

        // get result
        List<EduCostStat> eduCostStatList = this.eduCostStatRepository.findByYearAndStateAndTypeAndLengthAndExpense(
                request.getYear(), request.getState(), type, length, expense
        );

        // save to mongodb
        eduCostStatList.forEach(eduCostStat -> this.eduCostStatQueryOneRepository.save(
                new EduCostStatQueryOne(eduCostStat.getId(),eduCostStat.getValue())
        ));

        // send response
        List<EduCostStatQueryOneValue> eduCostStatQueryOneValues = eduCostStatList
                .stream()
                .map(eduCostStatItem -> EduCostStatQueryOneValue.newBuilder().setValue(eduCostStatItem.getValue())
                        .setId(eduCostStatItem.getId()).build())
                .collect(Collectors.toList());

        responseObserver.onNext(EduCostStatQueryOneResponse.newBuilder().addAllEduCostStatQueryOneValue(eduCostStatQueryOneValues).build());
        responseObserver.onCompleted();
    }
}
