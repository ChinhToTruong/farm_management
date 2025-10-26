package com.example.zev.config;

import com.example.zev.constants.ErrorCode;
import com.example.zev.dto.response.ResponseData;
import com.example.zev.exception.BusinessException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AccessDeniedImpl implements AccessDeniedHandler {
    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=UTF-8");

        ResponseData<?> body = ResponseData.builder()
                .code(ErrorCode.FORBIDDEN_ERROR.getCode())
                .message(ErrorCode.FORBIDDEN_ERROR.getMessage())
                .build();

        objectMapper.writeValue(response.getOutputStream(), body);
    }
}
