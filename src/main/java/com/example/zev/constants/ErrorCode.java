package com.example.zev.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ErrorCode {
    UNKNOW_ERROR("1000", "Unknow error!"),
    ENTITY_NOT_FOUND("1", "Entity not found!"),
    SEARCH_FIELD_INVALID("2", "Search field invalid!"),
    ;

    private String code;
    private String message;
}
