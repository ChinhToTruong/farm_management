package com.example.zev.exception;

import com.example.zev.constants.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessException extends Exception {
    private String message;
    private String code;


    public BusinessException(String message) {
        super(message);
        this.message = message;
    }


    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.message = message;
    }

}
