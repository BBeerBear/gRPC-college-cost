package com.bbeerbear.grpccollegecost.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "eduCostStat")
public class EduCostStatItem {
    private int year;
    private String state;
    private String type;
    private String length;
    private String expense;
    private int value;
}
