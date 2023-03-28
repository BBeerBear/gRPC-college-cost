package com.bbeerbear.grpccollegecost.server.service;

import com.bbeerbear.grpccollegecost.server.EduCostStatQueryTwoGrpc;
import com.bbeerbear.grpccollegecost.server.repository.EduCostStatQueryTwoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootTest
public class EduCostStatQueryTwoServiceTest {
    @Autowired
    private EduCostStatQueryTwoService eduCostStatQueryTwoService;
    @Test
    public void getEduCostStatQueryTwo() {
    }
}