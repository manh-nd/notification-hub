package com.example.notificationhub.domain.common;

public record TenantId(String value) {
    public TenantId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("tenant id must not be blank");
        }
    }
}
