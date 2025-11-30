package com.example.zev.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "Item")
@Table(name = "items")
@Data
@FieldNameConstants
public class Item extends BaseEntity{
    @Transient
    private Category category; // Nhóm vật tư

    @Column(name = "category_id")
    private Long categoryId; // Nhóm vật tư

    @Column(length = 150)
    private String name; // Tên vật tư

    @Column(length = 20)
    private String unit; // Đơn vị tính


    private Integer initialQuantity; // Số lượng ban đầu


    private Integer reorderLevel; // Ngưỡng cảnh báo


    private Integer currentQuantity; // Số lượng hiện có

    @Transient
    private Location location; // Kho hoặc khu vực chứa

    @Column(name = "location_id")
    private Long locationId;
}
