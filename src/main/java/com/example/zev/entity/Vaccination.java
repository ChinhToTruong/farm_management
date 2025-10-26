package com.example.zev.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "Vaccination")
@Table(name = "vaccinations")
@Data
@FieldNameConstants
public class Vaccination extends BaseEntity{

    @ManyToOne
    private AnimalBatch animalBatch;

    private String vaccinationName;

    @OneToOne
    private User user;

    private LocalDateTime startDate;

    private LocalDateTime nextDate;

    private String note;
}
