package com.example.zev.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "permissions")
@Data
@FieldNameConstants
public class Permission extends BaseEntity implements Serializable {
    private String permissionName;
    private String permissionDescription;
}
