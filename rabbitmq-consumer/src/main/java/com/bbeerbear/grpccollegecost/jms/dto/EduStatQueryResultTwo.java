package com.bbeerbear.grpccollegecost.jms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class EduStatQueryResultTwo implements Serializable {
    private String state;
    private int overallExpense;
    private int year;
    private String type;
    private String length;
}
