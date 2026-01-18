package com.example.zev.controller;

import com.example.zev.dto.response.ResponseData;
import com.example.zev.service.SummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/summary")
@RequiredArgsConstructor
public class SummaryController {
  private final SummaryService summaryService;

  @GetMapping("/work-diary")
  public ResponseData workDiary(@RequestParam(value = "userId", required = false) Long userId) {
    return ResponseData.builder()
        .data(summaryService.getWorkDiarySummary(userId)).build();
  }
}
