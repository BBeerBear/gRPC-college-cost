package com.bbeerbear.grpccollegecost.server.service;

import com.bbeerbear.grpccollegecost.server.EduCostStatQueryOneGrpc;
import com.bbeerbear.grpccollegecost.server.EduCostStatQueryOneRequest;
import com.bbeerbear.grpccollegecost.server.EduCostStatQueryOneResponse;
import com.bbeerbear.grpccollegecost.server.EduCostStatQueryOneValue;
import com.bbeerbear.grpccollegecost.server.entity.EduCostStat;
import com.bbeerbear.grpccollegecost.server.entity.EduCostStatQueryOne;
import com.bbeerbear.grpccollegecost.server.repository.EduCostStatQueryOneRepository;
import com.bbeerbear.grpccollegecost.server.repository.EduCostStatRepository;
import com.bbeerbear.grpccollegecost.server.utils.EnumTransferUtil;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class EduCostStatQueryOneService extends EduCostStatQueryOneGrpc.EduCostStatQueryOneImplBase {
    private final EduCostStatRepository eduCostStatRepository;
    private final EduCostStatQueryOneRepository eduCostStatQueryOneRepository;

    public EduCostStatQueryOneService(EduCostStatRepository eduCostStatRepository, EduCostStatQueryOneRepository eduCostStatQueryOneRepository) {
        this.eduCostStatRepository = eduCostStatRepository;
        this.eduCostStatQueryOneRepository = eduCostStatQueryOneRepository;
    }

    @Override
    public void getEduCostStatQueryOne(EduCostStatQueryOneRequest request, StreamObserver<EduCostStatQueryOneResponse> responseObserver) {
        // get result from mongodb
        List<EduCostStat> eduCostStatList = this.eduCostStatRepository.findByYearAndStateAndTypeAndLengthAndExpense(
                request.getYear(),
                request.getState(),
                EnumTransferUtil.typeTransfer(request.getType()),
                EnumTransferUtil.lengthTransfer(request.getLength()),
                EnumTransferUtil.expenseTransfer(request.getExpense())
        );

        // send response
        List<EduCostStatQueryOneValue> eduCostStatQueryOneValues = eduCostStatList
                .stream()
                .map(eduCostStatItem -> EduCostStatQueryOneValue.newBuilder()
                        .setValue(eduCostStatItem.getValue())
                        .setId(eduCostStatItem.getId())
                        .build())
                .collect(Collectors.toList());

        responseObserver.onNext(EduCostStatQueryOneResponse.newBuilder()
                .addAllEduCostStatQueryOneValue(eduCostStatQueryOneValues)
                .build());
        responseObserver.onCompleted();

        // save to mongodb
        eduCostStatList.forEach(eduCostStat -> this.eduCostStatQueryOneRepository.save(
                new EduCostStatQueryOne(eduCostStat.getId(),eduCostStat.getValue())
        ));
    }
}
