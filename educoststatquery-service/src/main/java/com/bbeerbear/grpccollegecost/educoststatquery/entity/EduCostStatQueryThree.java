package com.bbeerbear.grpccollegecost.educoststatquery.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document
public class EduCostStatQueryThree {
    private String state;
    private int overallExpense;
}
