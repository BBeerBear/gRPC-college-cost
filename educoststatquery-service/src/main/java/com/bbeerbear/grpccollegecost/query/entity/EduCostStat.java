package com.bbeerbear.grpccollegecost.query.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "eduCostStat")
public class EduCostStat {
    @Id
    private String id;
    private int year;
    private String state;
    private String type;
    private String length;
    private String expense;
    private int value;
}
