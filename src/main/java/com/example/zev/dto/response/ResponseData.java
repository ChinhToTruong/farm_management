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
    private String message;
    @Builder.Default
    private String code = "200";
    private Map<String, String> errors;
    private T data;
}
