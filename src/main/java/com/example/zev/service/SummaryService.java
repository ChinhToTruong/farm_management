package com.example.zev.service;

import com.example.zev.dto.response.ResponseData;
import com.example.zev.dto.response.summary.WorkDiaryDailyStatusDTO;
import com.example.zev.repository.WorkDiaryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SummaryService {
  private final WorkDiaryRepository workDiaryRepository;

  public List<WorkDiaryDailyStatusDTO> getWorkDiarySummary(Long userId) {
    return workDiaryRepository.summarizeByDateAndStatus(userId);
  }
}
