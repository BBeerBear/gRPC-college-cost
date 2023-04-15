package com.bbeerbear.grpccollegecost.aggregator.dto;

import lombok.Data;

@Data
public class EduStatRequestCondition {
    private int year;
    private String state;
    private String type;
    private String length;
    private String expense;
}
