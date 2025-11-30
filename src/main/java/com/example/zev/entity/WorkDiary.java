package com.example.zev.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "WorkDiary")
@Table(name = "work_diaries")
@Data
@FieldNameConstants
public class WorkDiary extends BaseEntity{

    @Column(name = "user_id")
    private Long userId;
    @Transient
    private User user; // Người thực hiện

    @Column(name = "crop_season_id")
    private Long cropSeasonId;

    @Transient
    private CropSeason cropSeason; // Vụ mùa liên quan

    @Column(name = "batch_id")
    private Long batchId;

    @Transient
    private AnimalBatch batch; // Có thể null - công việc cho 1 đàn cụ thể

    @Column(name = "plant_id")
    private Long plantId;

    @Transient
    private Plant plant; // Có thể null - công việc cho 1 cây cụ thể

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime workDate; // Ngày thực hiện

    @Column(length = 100)
    private String taskName; // Tên công việc

    private String description; // Mô tả

    @Pattern(regexp = "^PENDING|DONE|DELAYED$")
    private String status; // Trạng thái: pending, done, delayed
}
