package com.bbeerbear.grpccollegecost.query.repository;


import com.bbeerbear.grpccollegecost.query.entity.EduCostStatQueryThree;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EduCostStatQueryThreeRepository extends MongoRepository<EduCostStatQueryThree, String> {
}
