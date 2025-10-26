package com.example.zev.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "Location")
@Table(name = "locations")
@Data
@FieldNameConstants
public class Location extends BaseEntity{

    @ManyToOne
    private User user;

    @Column(name = "location_name")
    @NotBlank(message = "locationName is required")
    private String locationName;

    @Column(name = "area_size")
    private String areaSize;

    @Column(name = "type")
    @Pattern(regexp = "^ANIMAL|CROP|MIXED$", message = "animalType must be animal,crop, mixed")
    private String type;

    @NotBlank(message = "description is required")
    private String description;
}

