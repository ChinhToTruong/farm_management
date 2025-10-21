package com.example.zev.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseData<T> {
    private String message;
    @Builder.Default
    private String code = "200";
    private T data;
}
