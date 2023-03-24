package com.bbeerbear.grpccollegecost.repository;

import com.bbeerbear.grpccollegecost.entity.EduCostStatQueryOneItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EduCostStatQueryOneRepository extends MongoRepository<EduCostStatQueryOneItem, String> {

}
