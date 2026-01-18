package com.example.zev.service;

import com.example.zev.entity.Notification;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {


  private final SimpMessagingTemplate messagingTemplate;

  public void notifyUser(Long userId, Notification n) {
    messagingTemplate.convertAndSend(
        "/topic/notification." + userId,
        n
    );
  }

}
