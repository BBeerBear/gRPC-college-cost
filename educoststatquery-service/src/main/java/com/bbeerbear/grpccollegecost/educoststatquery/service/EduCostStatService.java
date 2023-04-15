package com.bbeerbear.grpccollegecost.educoststatquery.service;

import com.bbeerbear.grpccollegecost.educoststatquery.entity.EduCostStat;
import com.bbeerbear.grpccollegecost.educoststatquery.repository.EduCostStatRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * store the data from csv file to the MongoDB
 */
@Service
public class EduCostStatService {
    private final EduCostStatRepository eduCostStatRepository;

    public EduCostStatService(EduCostStatRepository eduCostStatRepository) {
        this.eduCostStatRepository = eduCostStatRepository;
    }

    public void uploadCsvToMongo() throws IOException, CsvException {
        if(this.eduCostStatRepository.count()>0) return;

        Path path = new File(getClass().getResource("/nces330_20.csv").getFile()).toPath();
        Reader reader = Files.newBufferedReader(path);
        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();

        List<EduCostStat> eduCostStatList = new ArrayList<>();;
        List<String[]> rows = csvReader.readAll();
        for (String[] row : rows) {
            EduCostStat eduCostStatItem = new EduCostStat();
            eduCostStatItem.setYear(Integer.parseInt(row[0]));
            eduCostStatItem.setState(row[1]);
            eduCostStatItem.setType(row[2]);
            eduCostStatItem.setLength(row[3]);
            eduCostStatItem.setExpense(row[4]);
            eduCostStatItem.setValue(Integer.parseInt(row[5]));
            eduCostStatList.add(eduCostStatItem);
        }
        this.eduCostStatRepository.saveAll(eduCostStatList);
    }

}
