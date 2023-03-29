package com.bbeerbear.grpccollegecost.server.repository;

import com.bbeerbear.grpccollegecost.server.entity.EduCostStatQueryFive;
import com.bbeerbear.grpccollegecost.server.entity.EduCostStatQueryFour;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EduCostStatQueryFiveRepository extends MongoRepository<EduCostStatQueryFive, String> {
}
