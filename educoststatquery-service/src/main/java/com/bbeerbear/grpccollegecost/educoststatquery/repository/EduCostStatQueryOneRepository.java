package com.bbeerbear.grpccollegecost.educoststatquery.repository;

import com.bbeerbear.grpccollegecost.educoststatquery.entity.EduCostStatQueryOne;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EduCostStatQueryOneRepository extends MongoRepository<EduCostStatQueryOne, String> {
}
