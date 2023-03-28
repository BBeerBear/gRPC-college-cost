package com.bbeerbear.grpccollegecost.client.service;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
public class EduCostStatQueryServiceTest {
    @Autowired
    private EduCostStatQueryService eduCostStatQueryService;
    @Test
    public void eduStatQueryOne() {
    }
    @Test
    public void eduStatQueryTwo() {
        eduCostStatQueryService.eduStatQueryTwo(2013,null,null);
    }
    @Test
    public void eduStatQueryThree(){
        eduCostStatQueryService.eduStatQueryThree(2013,null,null);
    }
    @Test
    public void eduStatQueryFour(){
        eduCostStatQueryService.eduStatQueryFour();
    }
}