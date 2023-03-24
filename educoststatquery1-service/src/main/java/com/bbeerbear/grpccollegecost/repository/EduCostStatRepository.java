package com.bbeerbear.grpccollegecost.repository;

import com.bbeerbear.grpccollegecost.entity.EduCostStatItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EduCostStatRepository extends MongoRepository<EduCostStatItem, String> {
}
