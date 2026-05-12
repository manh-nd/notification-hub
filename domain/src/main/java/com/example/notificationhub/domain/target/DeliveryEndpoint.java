package com.example.notificationhub.domain.target;

public sealed interface DeliveryEndpoint permits EmailEndpoint, SmsEndpoint, WhatsAppEndpoint, WebhookEndpoint {
    String value();
    String channelType();
}
