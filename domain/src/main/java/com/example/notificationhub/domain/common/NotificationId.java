package com.example.notificationhub.domain.common;

import java.util.UUID;

public record NotificationId(UUID value) {
    public NotificationId {
        if (value == null) {
            throw new IllegalArgumentException("notification id must not be null");
        }
    }

    public static NotificationId newId() {
        return new NotificationId(UUID.randomUUID());
    }
}
