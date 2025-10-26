package com.example.zev.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "AnimalBatch")
@Table(name = "animal_batch")
@Data
@FieldNameConstants
public class AnimalBatch extends BaseEntity{

    private String batchName;

    private String animalType;

    private Integer quantityStart;

    private Integer quantityCurrent;

    private String note;

    @Pattern(regexp = "^ACTIVE|SOLD|COMPLETED|CANCELED$")
    private String status;

    private LocalDateTime startDate;

    private LocalDateTime expectedEndDate;

    @OneToOne(cascade = CascadeType.ALL)
    private Location location;

    @OneToOne(cascade = CascadeType.ALL)
    private CropSeason season;
}
