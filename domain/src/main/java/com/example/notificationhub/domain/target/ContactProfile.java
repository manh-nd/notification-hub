package com.example.notificationhub.domain.target;

import java.util.Optional;

public record ContactProfile(String email, String phone, String whatsapp, String webhookUrl) {
    public Optional<DeliveryEndpoint> endpointByChannel(String channelType) {
        return switch (channelType) {
            case "EMAIL" -> Optional.ofNullable(email).map(EmailEndpoint::new).map(e -> (DeliveryEndpoint) e);
            case "SMS" -> Optional.ofNullable(phone).map(SmsEndpoint::new).map(e -> (DeliveryEndpoint) e);
            case "WHATSAPP" -> Optional.ofNullable(whatsapp).map(WhatsAppEndpoint::new).map(e -> (DeliveryEndpoint) e);
            case "WEBHOOK" -> Optional.ofNullable(webhookUrl).map(WebhookEndpoint::new).map(e -> (DeliveryEndpoint) e);
            default -> Optional.empty();
        };
    }
}
