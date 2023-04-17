package com.bbeerbear.grpccollegecost.jms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EduStatQueryResultOne implements Serializable {
    private String id;
    private int cost;
    private int year;
    private String state;
    private String type;
    private String length;
    private String expense;
}
