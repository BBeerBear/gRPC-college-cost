package com.bbeerbear.grpccollegecost;

import com.bbeerbear.grpccollegecost.repository.EduCostStatRepository;
import com.bbeerbear.grpccollegecost.service.CsvToMongoService;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.IOException;

@SpringBootApplication
@EnableMongoRepositories("com.bbeerbear.grpccollegecost.repository")
public class EduCostStatQueryApplication {

    public static void main(String[] args) throws IOException, CsvException {
        SpringApplication.run(EduCostStatQueryApplication.class, args);
    }
}
