package com.bbeerbear.grpccollegecost.aggregator.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EduCostStatQueryServiceTest {
    @Autowired
    private EduCostStatQueryService eduCostStatQueryService;
    @Test
    public void eduStatQueryOne() {
    }
    @Test
    public void eduStatQueryTwo() {
        eduCostStatQueryService.eduStatQueryTwo(2013,"Private","4-year");
    }
    @Test
    public void eduStatQueryThree(){
        eduCostStatQueryService.eduStatQueryThree(2013,"Private","4-year");
    }
    @Test
    public void eduStatQueryFour(){
        eduCostStatQueryService.eduStatQueryFour(1,"Private","4-year");
    }
    @Test void eduStatQueryFive(){
        eduCostStatQueryService.eduStatQueryFive(2013, "Private", "4-year");
    }
}