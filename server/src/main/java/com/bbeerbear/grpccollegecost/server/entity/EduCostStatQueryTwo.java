package com.bbeerbear.grpccollegecost.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document
public class EduCostStatQueryTwo {
    private String state;
    private int overallExpense;
}
