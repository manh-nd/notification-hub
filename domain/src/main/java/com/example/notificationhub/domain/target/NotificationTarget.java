package com.example.notificationhub.domain.target;

public record NotificationTarget(String userId, ContactProfile profile) {
    public NotificationTarget {
        if (userId == null || userId.isBlank()) throw new IllegalArgumentException("userId must not be blank");
        if (profile == null) throw new IllegalArgumentException("profile must not be null");
    }
}
