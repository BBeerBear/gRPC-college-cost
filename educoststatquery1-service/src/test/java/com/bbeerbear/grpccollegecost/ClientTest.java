package com.bbeerbear.grpccollegecost;

import com.bbeerbear.grpccollegecost.service.CsvToMongoService;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;

import java.io.IOException;
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClientTest {
    @Autowired
    private CsvToMongoService csvToMongoService;
    @Test
    public void testCsvToMongoService() throws IOException, CsvException {
        String csvFilePath = this.getClass().getClassLoader().getResource("nces330_20.csv").getPath();
        csvToMongoService.uploadCsvToMongo();
    }
}
