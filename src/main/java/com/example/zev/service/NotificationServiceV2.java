package com.example.zev.service;

import com.example.zev.entity.Notification;
import com.example.zev.repository.NotificationRepository;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class NotificationServiceV2 {


  private final NotificationRepository repo;

  // map userId -> SSE sink
  private final Map<Long, Sinks.Many<Notification>> sinks = new ConcurrentHashMap<>();

  public Notification createNotification(Long userId, String title, String message) {
    Notification n = new Notification();
    n.setUserId(userId);
    n.setTitle(title);
    n.setMessage(message);
    n = repo.save(n);

    // push notification real-time
    sinks.computeIfAbsent(userId, k -> Sinks.many().multicast().onBackpressureBuffer())
        .tryEmitNext(n);

    return n;
  }

  public List<Notification> getUnread(Long userId) {
    return repo.findByUserIdAndReadFalseOrderByCreatedAtDesc(userId);
  }

  public Flux<Notification> streamNotifications(Long userId) {
    return sinks
        .computeIfAbsent(userId, k -> Sinks.many().multicast().onBackpressureBuffer())
        .asFlux()
        .publishOn(Schedulers.boundedElastic());
  }

  public void markAsRead(Long id) {
    repo.findById(id).ifPresent(n -> {
      n.setRead(true);
      repo.save(n);
    });
  }

}
