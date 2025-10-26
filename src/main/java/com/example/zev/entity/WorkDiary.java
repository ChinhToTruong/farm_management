package com.example.zev.entity;

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

    @OneToOne(cascade = CascadeType.ALL)
    private User user; // Người thực hiện

    @ManyToOne
    private CropSeason cropSeason; // Vụ mùa liên quan

    @ManyToOne
    private AnimalBatch batch; // Có thể null - công việc cho 1 đàn cụ thể

    @ManyToOne
    private Plant plant; // Có thể null - công việc cho 1 cây cụ thể

    private LocalDateTime workDate; // Ngày thực hiện

    @Column(length = 100)
    private String taskName; // Tên công việc

    private String description; // Mô tả

    @Pattern(regexp = "^PENDING|DONE|DELAYED$")
    private String status; // Trạng thái: pending, done, delayed
}
