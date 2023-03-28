package com.bbeerbear.grpccollegecost.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EduStatQueryResultTwo {
    private String state;
    private int overallExpense;
}
