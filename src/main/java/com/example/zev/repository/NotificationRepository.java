package com.example.zev.repository;

import com.example.zev.entity.Notification;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
  List<Notification> findByUserIdAndReadFalseOrderByCreatedAtDesc(Long userId);
}
