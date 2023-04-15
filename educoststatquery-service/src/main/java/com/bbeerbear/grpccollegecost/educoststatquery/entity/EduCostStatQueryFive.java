package com.bbeerbear.grpccollegecost.educoststatquery.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document
public class EduCostStatQueryFive {
    private String region;
    private double averageOverallExpense;
}
