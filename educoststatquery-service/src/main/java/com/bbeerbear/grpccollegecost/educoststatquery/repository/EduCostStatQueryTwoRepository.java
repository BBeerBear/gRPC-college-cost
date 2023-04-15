package com.bbeerbear.grpccollegecost.educoststatquery.repository;


import com.bbeerbear.grpccollegecost.educoststatquery.entity.EduCostStatQueryTwo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EduCostStatQueryTwoRepository extends MongoRepository<EduCostStatQueryTwo, String> {
}
