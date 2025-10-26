package com.example.zev.controller;

import com.example.zev.dto.request.AuthenticationRequest;
import com.example.zev.dto.response.ResponseData;
import com.example.zev.exception.BusinessException;
import com.example.zev.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public ResponseData<?> refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, BusinessException {
        return ResponseData.builder()
                .data(authService.refreshToken(request, response))
                .build();
    }

    @GetMapping("/forgot-password")
    public ResponseData<?> forgotPassword(@RequestBody String email){
        return ResponseData.builder()
                .data(authService.forgotPassword(email))
                .build();
    }

    @PostMapping("/logout")
    public ResponseData<?> logout(@RequestParam("token") String token) throws BusinessException {
        return ResponseData.builder()
                .data(authService.logout(token))
                .build();
    }

}
