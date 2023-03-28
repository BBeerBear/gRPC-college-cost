package com.bbeerbear.grpccollegecost.server.repository;


import com.bbeerbear.grpccollegecost.server.entity.EduCostStatQueryThree;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EduCostStatQueryThreeRepository extends MongoRepository<EduCostStatQueryThree, String> {
}
