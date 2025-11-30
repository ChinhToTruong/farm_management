package com.example.zev.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
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

    @Transient
    private AnimalBatch animalBatch;


    @Column(name= "animal_batch_id")
    private Long batchId;

    private String vaccinationName;

    @Transient
    private User user;

    @Column(name = "user_id")
    private Long userId;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime nextDate;

    private String note;
}
