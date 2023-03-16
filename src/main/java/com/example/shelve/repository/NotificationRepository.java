package com.example.shelve.repository;

import com.example.shelve.dto.response.NotificationResponse;
import com.example.shelve.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> getNotificationByAccount_Id(Long id);
}
