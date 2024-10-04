package com.evizzo.notification.controllers;

import com.evizzo.notification.entities.Notification;
import com.evizzo.notification.services.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/notification")
public class NotificationController {
    private final NotificationService notificationService;

    /**
     * Marks a notification's read status.
     *
     * @param notificationId the ID of the notification to update
     * @param isRead the new read status (true for read, false for unread)
     * @return a {@link ResponseEntity} with HTTP status 200 (OK) if the operation was successful
     */
    @PutMapping("/{notificationId}/mark-read-status")
    public ResponseEntity<Void> markReadStatus(
            @PathVariable UUID notificationId,
            @RequestParam boolean isRead) {

        notificationService.markReadStatus(notificationId, isRead);
        return ResponseEntity.ok().build();
    }

    /**
     * Creates a new notification.
     *
     * @param notification the notification object to create
     * @return a {@link ResponseEntity} containing the created notification and HTTP status 201 (Created)
     */
    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification){
        return new ResponseEntity<>(notificationService.createNotification(notification), HttpStatus.CREATED);
    }

    /**
     * Retrieves all notifications for a specific person.
     *
     * @param personUsername the username of the person whose notifications are being retrieved
     * @return a {@link ResponseEntity} containing a list of notifications and HTTP status 200 (OK)
     */
    @GetMapping("/user/{personUsername}")
    public ResponseEntity<List<Notification>> getNotificationsByPersonUsername(@PathVariable String personUsername) {
        return ResponseEntity.ok(notificationService.getNotificationsByPersonUsername(personUsername));
    }
}
