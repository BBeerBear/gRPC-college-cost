package com.bbeerbear.grpccollegecost.query.repository;

import com.bbeerbear.grpccollegecost.query.entity.EduCostStatQueryOne;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EduCostStatQueryOneRepository extends MongoRepository<EduCostStatQueryOne, String> {
}
