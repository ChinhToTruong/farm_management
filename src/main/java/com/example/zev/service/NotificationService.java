package com.example.zev.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {


  private static final Logger log = LoggerFactory.getLogger(NotificationService.class);
  private final SimpMessagingTemplate messagingTemplate;

  public void pushToUser(String username, String message) {
    log.info("pushToUser: " + username + " " + message);
//    messagingTemplate.convertAndSendToUser(
//        username,
//        "/queue/notifications",
//        message
//    );

    messagingTemplate.convertAndSend("/user/totruongching@gmail.com/queue",  "message");
  }

  public void notifyUser(String userId, String message) {
    messagingTemplate.convertAndSend(
        "/queue/notifications/" + userId,
        message
    );
  }

}
