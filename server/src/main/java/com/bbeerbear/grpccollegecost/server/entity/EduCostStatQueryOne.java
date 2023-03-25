package com.bbeerbear.grpccollegecost.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
public class EduCostStatQueryOne {
  private String id;
  private int value;
}
