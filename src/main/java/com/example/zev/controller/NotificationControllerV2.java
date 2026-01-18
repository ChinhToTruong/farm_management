package com.example.zev.controller;

import com.example.zev.entity.Notification;
import com.example.zev.service.NotificationService;
import com.example.zev.service.NotificationServiceV2;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Slf4j
@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationControllerV2 {
  private final NotificationServiceV2 service;

  // SSE stream
  @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<Notification> stream(@RequestParam Long userId) {
    return service.streamNotifications(userId)
        .doOnError(e -> log.error("SSE error", e))
        .onErrorResume(e -> Flux.empty());
  }

  // fetch danh sách notification chưa đọc
  @GetMapping("/unread")
  public List<Notification> getUnread(@RequestParam Long userId) {
    return service.getUnread(userId);
  }

  // tạo notification (ví dụ admin tạo)
  @PostMapping
  public Notification create(@RequestBody Map<String, String> body) {
    Long userId = Long.parseLong(body.get("userId"));
    String title = body.get("title");
    String message = body.get("message");
    return service.createNotification(userId, title, message);
  }

  // đánh dấu đã đọc
  @PostMapping("/read/{id}")
  public void markAsRead(@PathVariable Long id) {
    service.markAsRead(id);
  }
}
