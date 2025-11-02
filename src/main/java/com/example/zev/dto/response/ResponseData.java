package com.example.zev.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseData<T> {
    @Builder.Default
    private String message = "success";
    @Builder.Default
    private String code = "200";
    private Map<String, String> errors;
    private T data;
}
