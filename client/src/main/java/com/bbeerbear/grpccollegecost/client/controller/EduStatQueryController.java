package com.bbeerbear.grpccollegecost.client.controller;

import com.bbeerbear.grpccollegecost.client.dto.EduStatQueryResultOne;
import com.bbeerbear.grpccollegecost.client.dto.EduStatRequestCondition;
import com.bbeerbear.grpccollegecost.client.service.EduCostStatQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
