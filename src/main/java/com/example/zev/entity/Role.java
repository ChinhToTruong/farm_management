package com.example.zev.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "Role")
@Table(name = "roles")
@Data
@FieldNameConstants
public class Role extends BaseEntity implements Serializable {

//    @Pattern(regexp = "^FARMER|ENGINEER|ADMIN$", message = "roleName must be admin, engineer, admin")
    private String roleName;
    private String roleDescription;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Permission> permissions;
}
