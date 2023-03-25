package com.bbeerbear.grpccollegecost.client;

import com.bbeerbear.grpccollegecost.client.dto.EduStatQueryResultOne;
import com.bbeerbear.grpccollegecost.client.service.EduCostStatQueryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class EduStatQueryTest {
    @Autowired
    private EduCostStatQueryService eduCostStatQueryService;

    @Test
    public void queryOneTest(){
        List<EduStatQueryResultOne> eduStatQueryResultOnes = eduCostStatQueryService.eduStatQueryOne(2013, "Alabama", "Private", "4-year", "Fees/Tuition");
        eduStatQueryResultOnes.stream().forEach(System.out::println);
    }
}
