package com.example.notificationhub.domain.event;

import com.example.notificationhub.domain.common.NotificationId;
import java.time.Instant;

public interface DomainEvent {
    NotificationId notificationId();
    Instant occurredAt();
}
