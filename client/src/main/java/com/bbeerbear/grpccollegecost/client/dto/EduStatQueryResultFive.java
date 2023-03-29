package com.bbeerbear.grpccollegecost.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EduStatQueryResultFive {
    private String region;
    private double averageOverallExpense;
}
