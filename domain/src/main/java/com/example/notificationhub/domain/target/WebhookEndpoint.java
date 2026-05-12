package com.example.notificationhub.domain.target;

import java.net.URI;

public record WebhookEndpoint(String value) implements DeliveryEndpoint {
    public WebhookEndpoint {
        if (value == null || value.isBlank()) throw new IllegalArgumentException("invalid webhook endpoint");
        URI.create(value);
    }
    @Override
    public String channelType() { return "WEBHOOK"; }
}
