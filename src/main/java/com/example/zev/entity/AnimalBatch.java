package com.example.zev.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
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

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime expectedEndDate;

    private Long locationId;

    private Long cropSeasonId;

    @Transient
    private Location location;

    @Transient
    private CropSeason season;
}
