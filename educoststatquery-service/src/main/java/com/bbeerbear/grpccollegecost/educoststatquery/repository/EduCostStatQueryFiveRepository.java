package com.bbeerbear.grpccollegecost.educoststatquery.repository;

import com.bbeerbear.grpccollegecost.educoststatquery.entity.EduCostStatQueryFive;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EduCostStatQueryFiveRepository extends MongoRepository<EduCostStatQueryFive, String> {
}
