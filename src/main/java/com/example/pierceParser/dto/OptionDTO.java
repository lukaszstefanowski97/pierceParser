package com.example.pierceParser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionDTO {

    private String code;

    private String attribute;

    private Integer sortOrder;

    private String label;

}
