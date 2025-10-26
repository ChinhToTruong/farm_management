package com.example.zev.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "Category")
@Table(name = "categories")
@Data
@FieldNameConstants
public class Category extends BaseEntity{
    private String name;
    private String description;
}
