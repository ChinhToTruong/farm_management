package com.example.zev.dto.request;

import com.example.zev.constants.FilterOperator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilterRequest implements Serializable {
    private String field;
    private String value;
    private String operator;
}
