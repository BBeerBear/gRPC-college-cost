package com.bbeerbear.grpccollegecost.server.repository;


import com.bbeerbear.grpccollegecost.server.entity.EduCostStatQueryTwo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EduCostStatQueryTwoRepository extends MongoRepository<EduCostStatQueryTwo, String> {
}
