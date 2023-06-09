package com.bbeerbear.grpccollegecost.query.repository;

import com.bbeerbear.grpccollegecost.query.entity.EduCostStat;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EduCostStatRepository extends MongoRepository<EduCostStat, String> {

    List<EduCostStat> findByYearAndStateAndTypeAndLengthAndExpense(int year, String state,
                                                                   String type, String length, String expense);
}
