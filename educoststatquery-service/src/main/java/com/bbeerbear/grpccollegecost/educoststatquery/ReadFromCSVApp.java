package com.bbeerbear.grpccollegecost.educoststatquery;

import com.bbeerbear.grpccollegecost.educoststatquery.service.EduCostStatService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ReadFromCSVApp implements ApplicationRunner {
    private final EduCostStatService eduCostStatService;

    public ReadFromCSVApp(EduCostStatService eduCostStatService) {
        this.eduCostStatService = eduCostStatService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        eduCostStatService.uploadCsvToMongo();
    }
}
