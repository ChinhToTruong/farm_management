package com.example.zev.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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

    @ManyToOne
    private CropSeason cropSeason; // Vụ mùa liên quan

    @ManyToOne
    private Location location; // Khu vực trồng


    private String plantName; // Loại cây (enum)


    private String plantVariety; // Giống cây (enum)

    private Integer quantity; // Số lượng cây trồng

    private LocalDateTime sowDate; // Ngày gieo trồng

    private LocalDateTime harvestDate; // Ngày thu hoạch (dự kiến hoặc thực tế)

    @Pattern(regexp = "^GROWING|HARVESTED|FAILD")
    private String status; // Trạng thái: growing, harvested, failed

    private String notes; // Ghi chú
}
