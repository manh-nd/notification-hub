package com.example.notificationhub.domain.consent;

public record Topic(String value) {
    public Topic {
        if (value == null || value.isBlank()) throw new IllegalArgumentException("topic must not be blank");
    }
}
