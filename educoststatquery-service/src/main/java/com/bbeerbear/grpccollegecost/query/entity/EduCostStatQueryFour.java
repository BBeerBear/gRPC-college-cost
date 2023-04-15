package com.bbeerbear.grpccollegecost.query.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document
public class EduCostStatQueryFour {
    private String state;
//    @Field("Growth Rate (/%)")
    private double growthRate;
}
