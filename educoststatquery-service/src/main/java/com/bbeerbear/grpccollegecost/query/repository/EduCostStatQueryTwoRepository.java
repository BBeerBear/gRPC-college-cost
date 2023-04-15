package com.bbeerbear.grpccollegecost.query.repository;


import com.bbeerbear.grpccollegecost.query.entity.EduCostStatQueryTwo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EduCostStatQueryTwoRepository extends MongoRepository<EduCostStatQueryTwo, String> {
}
