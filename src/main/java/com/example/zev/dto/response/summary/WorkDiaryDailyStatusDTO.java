package com.example.zev.dto.response.summary;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkDiaryDailyStatusDTO {

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDateTime workDate;
  private String status;
  private Long total;



}
