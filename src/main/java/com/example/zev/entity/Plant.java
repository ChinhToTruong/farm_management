package com.example.zev.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "Plant")
@Table(name = "plants")
@Data
@FieldNameConstants
public class Plant extends BaseEntity{

    @Transient
    private CropSeason cropSeason; // Vụ mùa liên quan

    @Transient
    private Location location; // Khu vực trồng


    private String plantName; // Loại cây (enum)


    private String plantVariety; // Giống cây (enum)

    private Integer quantity; // Số lượng cây trồng

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime sowDate; // Ngày gieo trồng

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime harvestDate; // Ngày thu hoạch (dự kiến hoặc thực tế)

    @Pattern(regexp = "^GROWING|HARVESTED|FAILD")
    private String status; // Trạng thái: growing, harvested, failed

    private String notes; // Ghi chú

    private Long locationId;
    private Long cropSeasonId;
}
