package com.example.zev.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "Report")
@Table(name = "reports")
@Data
@FieldNameConstants
public class Report extends BaseEntity{

    @ManyToOne
    private User user; // Người lập báo cáo

    @ManyToOne
    private CropSeason cropSeason; // Vụ mùa liên quan

    @ManyToOne
    private AnimalBatch batch; // Nếu là báo cáo chăn nuôi

    @ManyToOne
    private Plant plant; // Nếu là báo cáo trồng trọt

    private LocalDateTime reportDate; // Ngày báo cáo

    @Column(length = 150)
    private String title; // Tiêu đề


    private String content; // Nội dung


    @Pattern(regexp = "^DAILY|WEEKLY|FINAL$")
    private String type; // Loại báo cáo: daily, weekly, final
}
