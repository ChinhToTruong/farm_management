package com.example.zev.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchRequest implements Serializable {
    @Builder.Default
    private int pageNo = 0;
    @Builder.Default
    private int pageSize = 10;
    @Builder.Default
    List<FilterRequest> filters = new ArrayList<>();
    @Builder.Default
    List<SortRequest> sorts = new ArrayList<>();
}
