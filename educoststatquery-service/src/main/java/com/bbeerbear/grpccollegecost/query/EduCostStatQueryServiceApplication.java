package com.bbeerbear.grpccollegecost.query;

import com.opencsv.exceptions.CsvException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.IOException;

@SpringBootApplication
@EnableMongoRepositories("com.bbeerbear.grpccollegecost.query")
public class EduCostStatQueryServiceApplication {

    public static void main(String[] args) throws IOException, CsvException {
        SpringApplication.run(EduCostStatQueryServiceApplication.class, args);
    }
}
