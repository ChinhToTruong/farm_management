package com.example.zev.entity;

import com.example.zev.constants.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
@Data
@FieldNameConstants
public class User extends BaseEntity implements UserDetails, Serializable {
    @Column(name = "name")
    @NotBlank(message = "name is required")
    private String name;

    @Column(name = "password")
    @NotBlank(message = "password is required")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "email")
    @NotBlank(message = "email is required")
    private String email;

    @NotBlank(message = "phone is required")
    @Column(name = "phone")
    private String phone;

//    @Transient
//    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Role role;

    @Column(name = "status")
    @NotBlank(message = "status is required")
    private String status;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(role.getRoleName().toUpperCase()));
        role.getPermissions().forEach(permission -> {
            String rolePermission = String.format("%s_%s", role.getRoleName().toUpperCase(), permission.getPermissionName().toUpperCase());
            authorities.add(new SimpleGrantedAuthority(rolePermission));
        });
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.status.equalsIgnoreCase(Status.ACTIVE.name());
    }
}
