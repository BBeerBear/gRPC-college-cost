package com.bbeerbear.grpccollegecost.aggregator.controller;

import com.bbeerbear.grpccollegecost.aggregator.dto.*;
import com.bbeerbear.grpccollegecost.aggregator.service.EduCostStatQueryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EduStatQueryController {
    private final EduCostStatQueryService eduCostStatQueryService;

    public EduStatQueryController(EduCostStatQueryService eduCostStatQueryService) {
        this.eduCostStatQueryService = eduCostStatQueryService;
    }

    @PostMapping("/edu-cost-stat-query-one")
    public List<EduStatQueryResultOne> eduCostStatQueryOne(
            @RequestBody EduStatRequestCondition eduStatRequestCondition){
        return this.eduCostStatQueryService.eduStatQueryOne(eduStatRequestCondition.getYear(),
                eduStatRequestCondition.getState(),
                eduStatRequestCondition.getType(),
                eduStatRequestCondition.getLength(),
                eduStatRequestCondition.getExpense());
    }
    @PostMapping("/edu-cost-stat-query-two")
    public List<EduStatQueryResultTwo> eduCostStatQueryTwo(
            @RequestBody EduStatRequestCondition eduStatRequestCondition){
        return this.eduCostStatQueryService.eduStatQueryTwo(eduStatRequestCondition.getYear(),
                eduStatRequestCondition.getType(),eduStatRequestCondition.getLength());
    }
    @PostMapping("/edu-cost-stat-query-three")
    public List<EduStatQueryResultTwo> eduCostStatQueryThree(
            @RequestBody EduStatRequestCondition eduStatRequestCondition){
        return this.eduCostStatQueryService.eduStatQueryThree(eduStatRequestCondition.getYear(),
                eduStatRequestCondition.getType(),eduStatRequestCondition.getLength());
    }
    @PostMapping("/edu-cost-stat-query-four/pastYears/{pastYears}")
    public List<EduStatQueryResultFour> eduCostStatQueryFour(@PathVariable int pastYears,
                                                 @RequestBody EduStatRequestCondition eduStatRequestCondition){
        return this.eduCostStatQueryService.eduStatQueryFour(pastYears,
                eduStatRequestCondition.getType(), eduStatRequestCondition.getLength());
    }
    @PostMapping("/edu-cost-stat-query-five")
    public List<EduStatQueryResultFive> eduCostStatQueryFive(
            @RequestBody EduStatRequestCondition eduStatRequestCondition){
        return this.eduCostStatQueryService.eduStatQueryFive(eduStatRequestCondition.getYear(),
                eduStatRequestCondition.getType(), eduStatRequestCondition.getLength());
    }
}
