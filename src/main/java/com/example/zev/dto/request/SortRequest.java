package com.example.zev.dto.request;

import com.example.zev.constants.SortValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SortRequest implements Serializable {
    private String field;
    private String direction;

}
