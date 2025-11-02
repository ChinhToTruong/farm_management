//package com.example.zev.controller;
//
//import com.example.zev.dto.request.SearchRequest;
//import com.example.zev.dto.response.ResponseData;
//import com.example.zev.entity.Location;
//import com.example.zev.entity.User;
//import com.example.zev.exception.BusinessException;
//import com.example.zev.service.LocationService;
//import com.example.zev.service.UserService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.concurrent.ExecutionException;
//
//@RestController
//@RequestMapping("/locations")
//@RequiredArgsConstructor
//public class LocationController {
//
//    private final LocationService service;
//
//    @PostMapping("/search")
//    public ResponseData<?> search(@RequestBody SearchRequest searchRequest) throws ExecutionException, InterruptedException, BusinessException, ClassNotFoundException {
//        return ResponseData.builder()
//                .data(service.search(searchRequest))
//                .build();
//    }
//
//    @PostMapping
//    public ResponseData<?> create(@RequestBody @Valid Location entity) throws BusinessException {
//        return ResponseData.builder()
//                .data(service.create(entity))
//                .build();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseData<?> findById(@PathVariable("id") Long id) throws ExecutionException, InterruptedException, BusinessException {
//        return ResponseData.builder()
//                .data(service.findById(id))
//                .build();
//    }
//
//    @PutMapping
//    public ResponseData<?> update(@RequestBody @Valid Location entity) throws BusinessException {
//        return ResponseData.builder()
//                .data(service.update(entity))
//                .build();
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseData<?> delete(@PathVariable("id") Long id) throws BusinessException {
//        service.deleteById(id);
//        return ResponseData.builder()
//                .data("Success!")
//                .build();
//    }
//
//    @PostMapping("/delete-list")
//    public ResponseData<?> deleteList(@RequestBody @Valid List<Long> ids) throws BusinessException {
//        service.deleteList(ids);
//        return ResponseData.builder()
//                .data("Success!")
//                .build();
//    }
//}
