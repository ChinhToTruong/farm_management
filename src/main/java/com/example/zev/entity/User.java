package com.example.zev.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
@Data
@FieldNameConstants
public class User extends BaseEntity{
    private String name;
}
