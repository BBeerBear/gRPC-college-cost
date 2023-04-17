package com.bbeerbear.grpccollegecost.jms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class EduStatQueryResultFive implements Serializable {
    private String region;
    private double averageOverallExpense;
    private int year;
    private String type;
    private String length;
}
