package com.example.zev.exception;

import com.example.zev.dto.response.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseData<?> handleException(Exception e) {
        return ResponseData.builder()
                .code("400")
                .message("Lỗi hệ thống: " + e.getMessage())
                .build();
    }


    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseData<?> handleBusinessException(BusinessException e) {
        return ResponseData.builder()
                .code(e.getCode())
                .message(e.getMessage())
                .build();
    }

}
