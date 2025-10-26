package com.example.zev.config;

import com.example.zev.constants.ErrorCode;
import com.example.zev.dto.response.ResponseData;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        ResponseData<?> body = ResponseData.builder()
                .code(ErrorCode.ACCESS_DENIED_ERROR.getCode())
                .message(ErrorCode.ACCESS_DENIED_ERROR.getMessage())
                .build();

        objectMapper.writeValue(response.getOutputStream(), body);
    }
}
