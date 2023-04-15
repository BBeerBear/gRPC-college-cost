package com.bbeerbear.grpccollegecost.educoststatquery.repository;


import com.bbeerbear.grpccollegecost.educoststatquery.entity.EduCostStatQueryThree;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EduCostStatQueryThreeRepository extends MongoRepository<EduCostStatQueryThree, String> {
}
