package com.bbeerbear.grpccollegecost.service;

import com.bbeerbear.grpccollegecost.entity.EduCostStatItem;
import com.bbeerbear.grpccollegecost.repository.EduCostStatRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class CsvToMongoService {
    @Autowired
    private EduCostStatRepository eduCostStatRepository;
    public void uploadCsvToMongo() throws IOException, CsvException {
        Path path = new File(getClass().getResource("/nces330_20.csv").getFile()).toPath();
        Reader reader = Files.newBufferedReader(path);
        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();

        List<String[]> rows = csvReader.readAll();
        for (String[] row : rows) {
            EduCostStatItem eduCostStatItem = new EduCostStatItem();
            eduCostStatItem.setYear(Integer.parseInt(row[0]));
            eduCostStatItem.setState(row[1]);
            eduCostStatItem.setType(row[2]);
            eduCostStatItem.setLength(row[3]);
            eduCostStatItem.setExpense(row[4]);
            eduCostStatItem.setValue(Integer.parseInt(row[5]));
            eduCostStatRepository.save(eduCostStatItem);
        }
    }
}
