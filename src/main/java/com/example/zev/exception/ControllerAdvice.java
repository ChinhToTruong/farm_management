package com.example.zev.exception;

import com.example.zev.constants.ErrorCode;
import com.example.zev.dto.response.ResponseData;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

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

    // 1️⃣ Khi validate @RequestBody thất bại
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseData<?> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseData.builder()
                .code(ErrorCode.SEARCH_FIELD_INVALID.getCode())
                .errors(errors)
                .build();
    }

    // 2️⃣ Khi validate @RequestParam, @PathVariable thất bại
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseData<?> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(cv -> {
            String field = cv.getPropertyPath().toString();
            errors.put(field, cv.getMessage());
        });
        return ResponseData.builder()
                .code(ErrorCode.SEARCH_FIELD_INVALID.getCode())
                .errors(errors)
                .build();
    }

    // 3️⃣ Khi validate dữ liệu form (BindException)
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseData<?> handleBindException(BindException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseData.builder()
                .code(ErrorCode.SEARCH_FIELD_INVALID.getCode())
                .errors(errors)
                .build();
    }

    @ExceptionHandler({UsernameNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseData<?> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return ResponseData.builder()
                .code(ErrorCode.ENTITY_NOT_FOUND.getCode())
                .message(ex.getMessage())
                .build();
    }

}
