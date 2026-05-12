package com.example.notificationhub.domain.port;

import com.example.notificationhub.domain.common.IdempotencyKey;
import com.example.notificationhub.domain.common.TenantId;
import com.example.notificationhub.domain.notification.Notification;
import java.util.Optional;

public interface NotificationRepository {
    Optional<Notification> findByIdempotencyKey(TenantId tenantId, IdempotencyKey key);
    Notification save(Notification notification);
}
