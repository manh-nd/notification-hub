package com.example.notificationhub.domain.notification;

public record MessageContent(String subject, String body) {
    public MessageContent {
        if (body == null || body.isBlank()) throw new IllegalArgumentException("body must not be blank");
    }
}
