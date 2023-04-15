package com.bbeerbear.grpccollegecost.query.repository;

import com.bbeerbear.grpccollegecost.query.entity.EduCostStatQueryFour;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EduCostStatQueryFourRepository extends MongoRepository<EduCostStatQueryFour, String> {
}
