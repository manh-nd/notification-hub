package com.example.notificationhub.domain.event;

import com.example.notificationhub.domain.common.NotificationId;
import java.time.Instant;

public record NotificationFailedEvent(NotificationId notificationId, String reason, Instant occurredAt) implements DomainEvent {
}
