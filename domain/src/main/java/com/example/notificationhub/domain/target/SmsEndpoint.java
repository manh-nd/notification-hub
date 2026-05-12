package com.example.notificationhub.domain.target;

public record SmsEndpoint(String value) implements DeliveryEndpoint {
    public SmsEndpoint {
        if (value == null || value.isBlank()) throw new IllegalArgumentException("invalid sms endpoint");
    }
    @Override
    public String channelType() { return "SMS"; }
}
