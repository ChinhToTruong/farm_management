package com.example.zev.controller;

import com.example.zev.dto.response.ResponseData;
import com.example.zev.entity.WorkDiary;
import com.example.zev.service.WorkDiaryService;
import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/work-diaries")
public class WorkDiaryController extends BaseController<WorkDiary> {

    protected WorkDiaryService service;


    protected WorkDiaryController(WorkDiaryService service) {
        super(service);
        this.service = service;
    }

    @GetMapping("/export")
    public ResponseData<?> export() throws IOException {
        return ResponseData.builder()
            .data(service.exportExcel())
            .build();
    }
}
