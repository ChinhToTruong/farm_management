package com.example.zev.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
    @ManyToOne
    private Category category; // Nhóm vật tư

    @Column(length = 150)
    private String name; // Tên vật tư

    @Column(length = 20)
    private String unit; // Đơn vị tính


    private Integer initialQuantity; // Số lượng ban đầu


    private Integer reorderLevel; // Ngưỡng cảnh báo


    private Integer currentQuantity; // Số lượng hiện có

    @ManyToOne
    private Location location; // Kho hoặc khu vực chứa
}
