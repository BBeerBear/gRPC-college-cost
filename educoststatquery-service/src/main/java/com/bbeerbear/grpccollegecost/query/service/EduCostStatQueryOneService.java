package com.bbeerbear.grpccollegecost.query.service;

import com.bbeerbear.grpccollegecost.query.EduCostStatQueryOneGrpc;
import com.bbeerbear.grpccollegecost.query.EduCostStatQueryOneRequest;
import com.bbeerbear.grpccollegecost.query.EduCostStatQueryOneResponse;
import com.bbeerbear.grpccollegecost.query.EduCostStatQueryOneValue;
import com.bbeerbear.grpccollegecost.query.entity.EduCostStat;
import com.bbeerbear.grpccollegecost.query.entity.EduCostStatQueryOne;
import com.bbeerbear.grpccollegecost.query.repository.EduCostStatQueryOneRepository;
import com.bbeerbear.grpccollegecost.query.repository.EduCostStatRepository;
import com.bbeerbear.grpccollegecost.query.utils.EnumTransferUtil;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Query the cost given specific year, state, type, length, expense;
 * and save the query as a document in a collection named EduCostStatQueryOne
 */
@GrpcService
public class EduCostStatQueryOneService extends EduCostStatQueryOneGrpc.EduCostStatQueryOneImplBase {
    private final EduCostStatRepository eduCostStatRepository;
    private final EduCostStatQueryOneRepository eduCostStatQueryOneRepository;

    public EduCostStatQueryOneService(EduCostStatRepository eduCostStatRepository,
                                      EduCostStatQueryOneRepository eduCostStatQueryOneRepository) {
        this.eduCostStatRepository = eduCostStatRepository;
        this.eduCostStatQueryOneRepository = eduCostStatQueryOneRepository;
    }

    @Override
    public void getEduCostStatQueryOne(EduCostStatQueryOneRequest request,
                                       StreamObserver<EduCostStatQueryOneResponse> responseObserver) {
        // get result from mongodb
        List<EduCostStat> eduCostStatList = this.eduCostStatRepository
                .findByYearAndStateAndTypeAndLengthAndExpense(
                    request.getYear(),
                    request.getState(),
                    EnumTransferUtil.typeTransfer(request.getType()),
                    EnumTransferUtil.lengthTransfer(request.getLength()),
                    EnumTransferUtil.expenseTransfer(request.getExpense()
                )
        );

        // save to mongodb
        this.eduCostStatQueryOneRepository.deleteAll();
        eduCostStatList.forEach(eduCostStat -> this.eduCostStatQueryOneRepository.save(
                new EduCostStatQueryOne(eduCostStat.getId(),eduCostStat.getValue())
        ));
        List<EduCostStatQueryOneValue> eduCostStatQueryOneValues = this.eduCostStatQueryOneRepository.findAll()
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
    }
}
