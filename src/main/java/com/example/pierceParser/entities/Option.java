package com.example.pierceParser.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Option {

    private String code;

    private String attribute;

    private Integer sortOrder;

    private Map<String, String> labels;
}
