package com.bbeerbear.grpccollegecost.server.repository;

import com.bbeerbear.grpccollegecost.server.entity.EduCostStatQueryOne;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EduCostStatQueryOneRepository extends MongoRepository<EduCostStatQueryOne, String> {

}
