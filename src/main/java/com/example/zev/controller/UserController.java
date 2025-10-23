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

    @PostMapping("/search")
    public ResponseData<?> search(@RequestBody SearchRequest searchRequest) throws ExecutionException, InterruptedException, BusinessException, ClassNotFoundException {
        return ResponseData.builder()
                .data(userService.search(searchRequest))
                .build();
    }

    @PostMapping
    public ResponseData<?> create(@RequestBody @Valid User user) throws BusinessException {
        return ResponseData.builder()
                .data(userService.create(user))
                .build();
    }

    @GetMapping("/{id}")
    public ResponseData<?> findById(@PathVariable("id") Long id) throws ExecutionException, InterruptedException, BusinessException {
        return ResponseData.builder()
                .data(userService.findById(id))
                .build();
    }

    @PutMapping
    public ResponseData<?> update(@RequestBody @Valid User user) throws BusinessException {
        return ResponseData.builder()
                .data(userService.update(user))
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseData<?> delete(@PathVariable("id") Long id) throws BusinessException {
        userService.deleteById(id);
        return ResponseData.builder()
                .data("Success!")
                .build();
    }

    @PostMapping("/delete-list")
    public ResponseData<?> deleteList(@RequestBody @Valid List<Long> ids) throws BusinessException {
        userService.deleteList(ids);
        return ResponseData.builder()
                .data("Success!")
                .build();
    }
}
