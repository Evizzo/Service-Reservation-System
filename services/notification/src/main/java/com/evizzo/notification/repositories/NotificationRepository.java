package com.evizzo.notification.repositories;

import com.evizzo.notification.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource(path = "notification")
public interface NotificationRepository extends JpaRepository<Notification, UUID> {
    List<Notification> findAllBySendToPersonUsernameOrderByTimestampDesc(String sendToPersonUsername);
}

// GET /notification - Retrieve all notifications.
// GET /notification/{id} - Retrieve a specific notification by ID.
// DELETE /notification/{id} - Delete a notification by ID.
// POST /notification - Create a new notification.
// PUT /notification/{id} - Update an existing notification by ID.
// PATCH /notification/{id} - Partially update an existing notification.
