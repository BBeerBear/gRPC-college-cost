package com.bbeerbear.grpccollegecost.client.controller;

import com.bbeerbear.grpccollegecost.client.dto.EduStatQueryResultFour;
import com.bbeerbear.grpccollegecost.client.dto.EduStatQueryResultOne;
import com.bbeerbear.grpccollegecost.client.dto.EduStatQueryResultTwo;
import com.bbeerbear.grpccollegecost.client.dto.EduStatRequestCondition;
import com.bbeerbear.grpccollegecost.client.service.EduCostStatQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EduStatQueryController {
    @Autowired
    private EduCostStatQueryService eduCostStatQueryService;

    @PostMapping("/edu-cost-stat-query-one")
    public List<EduStatQueryResultOne> eduCostStatQueryOne(@RequestBody EduStatRequestCondition eduStatRequestCondition){
        return this.eduCostStatQueryService.eduStatQueryOne(eduStatRequestCondition.getYear(),eduStatRequestCondition.getState(),
                eduStatRequestCondition.getType(), eduStatRequestCondition.getLength(),eduStatRequestCondition.getExpense());
    }
    @PostMapping("/edu-cost-stat-query-two")
    public List<EduStatQueryResultTwo> eduCostStatQueryTwo(@RequestBody EduStatRequestCondition eduStatRequestCondition){
        return this.eduCostStatQueryService.eduStatQueryTwo(eduStatRequestCondition.getYear(),eduStatRequestCondition.getType(),eduStatRequestCondition.getLength());
    }
    @PostMapping("/edu-cost-stat-query-three")
    public List<EduStatQueryResultTwo> eduCostStatQueryThree(@RequestBody EduStatRequestCondition eduStatRequestCondition){
        return this.eduCostStatQueryService.eduStatQueryThree(eduStatRequestCondition.getYear(),eduStatRequestCondition.getType(),eduStatRequestCondition.getLength());
    }
    @PostMapping("/edu-cost-stat-query-four/pastYears/{pastYears}")
    public List<EduStatQueryResultFour> eduCostStatQueryFour(@PathVariable int pastYears, @RequestBody EduStatRequestCondition eduStatRequestCondition){
        return this.eduCostStatQueryService.eduStatQueryFour(pastYears, eduStatRequestCondition.getType(), eduStatRequestCondition.getLength());
    }
}
