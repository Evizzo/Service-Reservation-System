package com.evizzo.notification.services;

import com.evizzo.notification.entities.Notification;
import com.evizzo.notification.repositories.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    @Transactional
    public void markReadStatus(UUID notificationId, boolean isRead) {
        Optional<Notification> notificationOptional = notificationRepository.findById(notificationId);

        if (notificationOptional.isPresent()) {
            Notification notification = notificationOptional.get();
            notification.setIsRead(isRead);
            notificationRepository.save(notification);
        } else {
            throw new IllegalArgumentException("Notification with ID " + notificationId + " not found");
        }
    }

    public Notification createNotification(Notification notification){
        notification.setTimestamp(LocalDateTime.now());
        notification.setMessage(notification.getMessage());
        notification.setSendToPersonUsername(notification.getSendToPersonUsername());

        return notificationRepository.saveAndFlush(notification);
    }

    public List<Notification> getNotificationsByPersonUsername(String personUsername) {
        return notificationRepository.findAllBySendToPersonUsernameOrderByTimestampDesc(personUsername);
    }
}
