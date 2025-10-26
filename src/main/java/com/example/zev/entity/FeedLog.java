package com.example.zev.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "FeedLog")
@Table(name = "feed_logs")
@Data
@FieldNameConstants
public class FeedLog extends BaseEntity {

    @ManyToOne
    private AnimalBatch animalBatch;

    private LocalDateTime feedDate;

    private String feedType;

    private BigDecimal quantity;

    private String unit;

    private String note;

    @ManyToOne
    private User user;
}
