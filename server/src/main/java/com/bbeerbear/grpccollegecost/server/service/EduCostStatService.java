package com.bbeerbear.grpccollegecost.server.service;

import com.bbeerbear.grpccollegecost.server.entity.EduCostStat;
import com.bbeerbear.grpccollegecost.server.repository.EduCostStatRepository;
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
import java.util.List;

@Service
public class EduCostStatService {
    @Autowired
    private EduCostStatRepository eduCostStatRepository;
    public void uploadCsvToMongo() throws IOException, CsvException {
        Path path = new File(getClass().getResource("/nces330_20.csv").getFile()).toPath();
        Reader reader = Files.newBufferedReader(path);
        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();

        List<String[]> rows = csvReader.readAll();
        for (String[] row : rows) {
            EduCostStat eduCostStatItem = new EduCostStat();
            eduCostStatItem.setYear(Integer.parseInt(row[0]));
            eduCostStatItem.setState(row[1]);
            eduCostStatItem.setType(row[2]);
            eduCostStatItem.setLength(row[3]);
            eduCostStatItem.setExpense(row[4]);
            eduCostStatItem.setValue(Integer.parseInt(row[5]));
            if(eduCostStatRepository.findByYearAndStateAndTypeAndLengthAndExpense(eduCostStatItem.getYear(),
                    eduCostStatItem.getState(),eduCostStatItem.getType(),eduCostStatItem.getLength(),
                    eduCostStatItem.getExpense()).size()==0)
                eduCostStatRepository.save(eduCostStatItem);
        }
    }

}
