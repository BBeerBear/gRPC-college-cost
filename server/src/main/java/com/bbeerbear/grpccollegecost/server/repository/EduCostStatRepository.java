package com.bbeerbear.grpccollegecost.server.repository;

import com.bbeerbear.grpccollegecost.server.entity.EduCostStat;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EduCostStatRepository extends MongoRepository<EduCostStat, String> {
    List<EduCostStat> findByYearOrStateOrTypeOrLengthOrExpense(int year, String state, String type, String length, String expense);
    List<EduCostStat> findByYearAndStateAndTypeAndLengthAndExpense(int year, String state, String type, String length, String expense);
}
