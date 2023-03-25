package com.bbeerbear.grpccollegecost.server;

import com.opencsv.exceptions.CsvException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.IOException;

@SpringBootApplication
@EnableMongoRepositories("com.bbeerbear.grpccollegecost.server")
public class ServerApplication {

    public static void main(String[] args) throws IOException, CsvException {
        SpringApplication.run(ServerApplication.class, args);
    }
}
