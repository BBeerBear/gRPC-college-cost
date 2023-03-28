package com.bbeerbear.grpccollegecost.server.repository;

import com.bbeerbear.grpccollegecost.server.entity.EduCostStatQueryFour;
import com.bbeerbear.grpccollegecost.server.entity.EduCostStatQueryOne;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EduCostStatQueryFourRepository extends MongoRepository<EduCostStatQueryFour, String> {
}
