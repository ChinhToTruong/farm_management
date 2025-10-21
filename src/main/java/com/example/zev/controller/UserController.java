package com.example.zev.controller;

import com.example.zev.dto.request.SearchRequest;
import com.example.zev.dto.response.ListResponse;
import com.example.zev.dto.response.ResponseData;
import com.example.zev.entity.User;
import com.example.zev.exception.BusinessException;
import com.example.zev.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/search")
    public ResponseData<?> search(@RequestBody SearchRequest searchRequest) throws ExecutionException, InterruptedException, BusinessException {
        return ResponseData.builder()
                .data(userService.search(searchRequest))
                .build();
    }
}
