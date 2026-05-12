package com.example.notificationhub.domain.event;

import com.example.notificationhub.domain.common.NotificationId;
import java.time.Instant;

public record NotificationSentEvent(NotificationId notificationId, Instant occurredAt) implements DomainEvent {
}
