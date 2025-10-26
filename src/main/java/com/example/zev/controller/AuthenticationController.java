package com.example.zev.controller;

import com.example.zev.dto.request.AuthenticationRequest;
import com.example.zev.dto.response.ResponseData;
import com.example.zev.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthService authService;


    @PostMapping("/login")
    public ResponseData<?> login(@RequestBody @Valid AuthenticationRequest request) {
        return ResponseData.builder()
                .data(authService.authenticate(request))
                .build();
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authService.refreshToken(request, response);
    }
}
