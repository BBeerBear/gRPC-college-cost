package com.bbeerbear.grpccollegecost.client.dto;

import lombok.Data;

import javax.annotation.Nullable;

@Data
public class EduStatRequestCondition {
    private int year;
    private String state;
    private String type;
    private String length;
    private String expense;
}
