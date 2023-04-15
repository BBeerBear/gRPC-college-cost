package com.bbeerbear.grpccollegecost.educoststatquery.repository;

import com.bbeerbear.grpccollegecost.educoststatquery.entity.EduCostStatQueryFour;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EduCostStatQueryFourRepository extends MongoRepository<EduCostStatQueryFour, String> {
}
