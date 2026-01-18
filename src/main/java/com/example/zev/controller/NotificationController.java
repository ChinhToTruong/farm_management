package com.example.zev.controller;

import com.example.zev.entity.Notification;
import com.example.zev.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class NotificationController {
  private final NotificationService service;

  @PostMapping("/send")
  public void send(
      @RequestParam Long userId,
      @RequestParam String title,
      @RequestParam String message
  ) {
    Notification n = Notification.builder()
        .userId(userId)
        .title(title)
        .message(message)
        .read(false)
        .build();
    service.notifyUser(userId, n);
  }

  private final NotificationService notificationService;

  @PostMapping("/notify/{userId}")
  public void push(
      @PathVariable Long userId,
      @RequestBody Notification dto
  ) {
    Notification n = new Notification();
    n.setTitle(dto.getTitle());
    n.setMessage(dto.getMessage());

    notificationService.notifyUser(userId, n);
  }
}
