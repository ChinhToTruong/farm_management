package com.example.zev.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;

@Entity(name = "Notification")
@Table(name = "notifications")
@Data
public class Notification {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long userId;
  private String title;
  private String message;
  private boolean read = false; // trạng thái đã đọc

  private LocalDateTime createdAt = LocalDateTime.now();
}
