package com.example.notificationhub.domain.notification;

import com.example.notificationhub.domain.common.IdempotencyKey;
import com.example.notificationhub.domain.common.NotificationId;
import com.example.notificationhub.domain.common.TenantId;
import com.example.notificationhub.domain.event.DomainEvent;
import com.example.notificationhub.domain.event.NotificationFailedEvent;
import com.example.notificationhub.domain.event.NotificationSentEvent;
import com.example.notificationhub.domain.target.DeliveryEndpoint;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public final class Notification {
    private final NotificationId id;
    private final IdempotencyKey idempotencyKey;
    private final TenantId tenantId;
    private final DeliveryEndpoint endpoint;
    private final MessageContent content;
    private NotificationStatus status;
    private String failureReason;
    private final List<DomainEvent> events = new ArrayList<>();

    public Notification(NotificationId id, IdempotencyKey idempotencyKey, TenantId tenantId, DeliveryEndpoint endpoint, MessageContent content) {
        this.id = id;
        this.idempotencyKey = idempotencyKey;
        this.tenantId = tenantId;
        this.endpoint = endpoint;
        this.content = content;
        this.status = NotificationStatus.PENDING;
    }

    public void markAsSent() {
        this.status = NotificationStatus.SENT;
        events.add(new NotificationSentEvent(id, Instant.now()));
    }

    public void fail(String reason) {
        this.status = NotificationStatus.FAILED;
        this.failureReason = reason;
        events.add(new NotificationFailedEvent(id, reason, Instant.now()));
    }

    public void markSkippedConsent() { this.status = NotificationStatus.SKIPPED_CONSENT; }
    public void markInvalidEndpoint(String reason) { this.status = NotificationStatus.INVALID_ENDPOINT; this.failureReason = reason; }

    public NotificationId id() { return id; }
    public IdempotencyKey idempotencyKey() { return idempotencyKey; }
    public TenantId tenantId() { return tenantId; }
    public DeliveryEndpoint endpoint() { return endpoint; }
    public MessageContent content() { return content; }
    public NotificationStatus status() { return status; }
    public String failureReason() { return failureReason; }
    public List<DomainEvent> pullEvents() {
        var copy = List.copyOf(events);
        events.clear();
        return copy;
    }
}
