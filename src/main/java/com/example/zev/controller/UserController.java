package com.example.zev.controller;

import com.example.zev.dto.request.SearchRequest;
import com.example.zev.dto.response.ListResponse;
import com.example.zev.dto.response.ResponseData;
import com.example.zev.entity.User;
import com.example.zev.exception.BusinessException;
import com.example.zev.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    @GetMapping("/search")
//    public ResponseData<?> search(@RequestBody SearchRequest searchRequest) throws ExecutionException, InterruptedException, BusinessException {
//        return ResponseData.builder()
//                .data(userService.search(searchRequest))
//                .build();
//    }

    @PostMapping
    public ResponseData<?> create(@RequestBody @Valid User user) {
        return ResponseData.builder()
                .data(userService.create(user))
                .build();
    }

    @GetMapping("/{id}")
    public ResponseData<?> search(@PathVariable("id") Long id) throws ExecutionException, InterruptedException, BusinessException {
        return ResponseData.builder()
                .data(userService.findById(id))
                .build();
    }
}
