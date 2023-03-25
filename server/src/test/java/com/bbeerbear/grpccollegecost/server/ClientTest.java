package com.bbeerbear.grpccollegecost.server;

import com.bbeerbear.grpccollegecost.server.service.EduCostStatService;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
@SpringBootTest
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClientTest {
    @Autowired
    private EduCostStatService csvToMongoService;
    @Test
    public void testEduCostStatRepositoryService() throws IOException, CsvException {
        String csvFilePath = this.getClass().getClassLoader().getResource("nces330_20.csv").getPath();
        csvToMongoService.uploadCsvToMongo();
    }
    @Test
    public void testEduCostStatQueryOneService() {

    }
}
