package com.example.zev.controller;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/test")
public class NotificationController {
  @Autowired
  private SimpMessagingTemplate messagingTemplate;

  @PostMapping("/push")
  public void push() {

    Authentication auth =
        SecurityContextHolder.getContext().getAuthentication();

    if (auth == null) {
      throw new RuntimeException("Unauthenticated");
    }

    String userId = auth.getName(); // từ JWT filter

    messagingTemplate.convertAndSendToUser(
        userId,
        "/queue/notifications",
        "🔥 Test notification"
    );
    log.info("Push notifications successful: " + userId);
  }
}
