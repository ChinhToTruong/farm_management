package com.example.zev.controller;

import com.example.zev.dto.request.EmailRequest;
import com.example.zev.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class SendEmailController {

    private final EmailService emailService;


    // Gửi email bằng POST với JSON body
    @PostMapping("/send")
    public String sendEmailPost(@RequestBody EmailRequest request) {
        emailService.sendEmail(request);
        return "Email sent to " + request.getTo();
    }
}
