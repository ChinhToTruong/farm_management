package com.example.zev.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "CropSeason")
@Table(name = "crop_seasons")
@Data
@FieldNameConstants
public class CropSeason extends BaseEntity{

    @NotBlank(message = "seasonName is required")
    private String seasonName;

    @NotNull(message = "startDate is required")
    private LocalDateTime startDate;

    @NotNull(message = "endDate is required")
    private LocalDateTime endDate;

    @Pattern(regexp = "^ANIMAL|CROP$", message = "type must be animal, crop")
    private String type;

    @Pattern(regexp = "^ACTIVE|COMPLETED|PAUSED", message = "status must be ACTIVE, COMPLETED, PAUSED")
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    private Location location;

}
