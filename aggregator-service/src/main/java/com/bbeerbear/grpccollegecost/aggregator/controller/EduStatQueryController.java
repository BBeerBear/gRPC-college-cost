package com.bbeerbear.grpccollegecost.aggregator.controller;

import com.bbeerbear.grpccollegecost.aggregator.dto.*;
import com.bbeerbear.grpccollegecost.aggregator.service.EduCostStatQueryService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EduStatQueryController {
    private final RabbitTemplate rabbitTemplate;
    private final EduCostStatQueryService eduCostStatQueryService;

    public EduStatQueryController(RabbitTemplate rabbitTemplate, EduCostStatQueryService eduCostStatQueryService) {
        this.rabbitTemplate = rabbitTemplate;
        this.eduCostStatQueryService = eduCostStatQueryService;
    }

    @PostMapping("/edu-cost-stat-query-one")
    public List<EduStatQueryResultOne> eduCostStatQueryOne(
            @RequestBody EduStatRequestCondition eduStatRequestCondition){
        int year = eduStatRequestCondition.getYear();
        String state = eduStatRequestCondition.getState();
        String type = eduStatRequestCondition.getType();
        String length = eduStatRequestCondition.getLength();
        String expense = eduStatRequestCondition.getExpense();
        List<EduStatQueryResultOne> eduStatQueryResultOnes = this.eduCostStatQueryService.eduStatQueryOne(year, state, type, length, expense);
        rabbitTemplate.convertAndSend("Topic-Exchange",
                String.format("cost.%d.%s.%s.%s",year,state,type,length),
                eduStatQueryResultOnes);
        return eduStatQueryResultOnes;
    }
    @PostMapping("/edu-cost-stat-query-two")
    public List<EduStatQueryResultTwo> eduCostStatQueryTwo(
            @RequestBody EduStatRequestCondition eduStatRequestCondition){
        int year = eduStatRequestCondition.getYear();
        String type = eduStatRequestCondition.getType();
        String length = eduStatRequestCondition.getLength();
        List<EduStatQueryResultTwo> eduStatQueryResultTwos = this.eduCostStatQueryService.eduStatQueryTwo(eduStatRequestCondition.getYear(),
                eduStatRequestCondition.getType(), eduStatRequestCondition.getLength());
        rabbitTemplate.convertAndSend("Topic-Exchange",
                String.format("top5.expensive.%d.%s.%s",year,type,length),
                eduStatQueryResultTwos);
        return eduStatQueryResultTwos;
    }
    @PostMapping("/edu-cost-stat-query-three")
    public List<EduStatQueryResultTwo> eduCostStatQueryThree(
            @RequestBody EduStatRequestCondition eduStatRequestCondition){
        List<EduStatQueryResultTwo> eduStatQueryResultThrees = this.eduCostStatQueryService.eduStatQueryThree(eduStatRequestCondition.getYear(),
                eduStatRequestCondition.getType(), eduStatRequestCondition.getLength());
        int year = eduStatRequestCondition.getYear();
        String type = eduStatRequestCondition.getType();
        String length = eduStatRequestCondition.getLength();
        rabbitTemplate.convertAndSend("Topic-Exchange",
                String.format("top5.economic.%d.%s.%s",year,type,length),
                eduStatQueryResultThrees);
        return eduStatQueryResultThrees;
    }
    @PostMapping("/edu-cost-stat-query-four/pastYears/{pastYears}")
    public List<EduStatQueryResultFour> eduCostStatQueryFour(@PathVariable int pastYears,
                                                 @RequestBody EduStatRequestCondition eduStatRequestCondition){
        String type = eduStatRequestCondition.getType();
        String length = eduStatRequestCondition.getLength();
        List<EduStatQueryResultFour> eduStatQueryResultFours = this.eduCostStatQueryService.eduStatQueryFour(pastYears,
                type, length);
        rabbitTemplate.convertAndSend("Topic-Exchange",
                String.format("top5.highestGrow.%d.%s.%s",pastYears,type,length),
                eduStatQueryResultFours);
        return eduStatQueryResultFours;
    }
    @PostMapping("/edu-cost-stat-query-five")
    public List<EduStatQueryResultFive> eduCostStatQueryFive(
            @RequestBody EduStatRequestCondition eduStatRequestCondition){
        int year = eduStatRequestCondition.getYear();
        String type = eduStatRequestCondition.getType();
        String length = eduStatRequestCondition.getLength();
        List<EduStatQueryResultFive> eduStatQueryResultFives = this.eduCostStatQueryService.eduStatQueryFive(eduStatRequestCondition.getYear(),
                eduStatRequestCondition.getType(), eduStatRequestCondition.getLength());
        rabbitTemplate.convertAndSend("Topic-Exchange",
                String.format("averageExpense.%d.%s.%s",year,type,length),
                eduStatQueryResultFives);
        return eduStatQueryResultFives;
    }
}
