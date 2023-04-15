package com.bbeerbear.grpccollegecost.query.repository;

import com.bbeerbear.grpccollegecost.query.entity.EduCostStatQueryFive;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EduCostStatQueryFiveRepository extends MongoRepository<EduCostStatQueryFive, String> {
}
