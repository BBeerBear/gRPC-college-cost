package com.bbeerbear.grpccollegecost.aggregator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class EduStatQueryResultFour implements Serializable {
    private String state;
    private double growthRate;
    private int pastYears;
    private String type;
    private String length;
}
