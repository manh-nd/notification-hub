package com.example.notificationhub.domain.target;

public record WhatsAppEndpoint(String value) implements DeliveryEndpoint {
    public WhatsAppEndpoint {
        if (value == null || value.isBlank()) throw new IllegalArgumentException("invalid whatsapp endpoint");
    }
    @Override
    public String channelType() { return "WHATSAPP"; }
}
