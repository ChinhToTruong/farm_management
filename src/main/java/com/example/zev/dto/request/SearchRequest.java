package com.example.zev.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchRequest implements Serializable {
    private int pageNo;
    private int pageSize;
    List<FilterRequest> filters;
    List<SortRequest> sorts;
    private String entity;
}
