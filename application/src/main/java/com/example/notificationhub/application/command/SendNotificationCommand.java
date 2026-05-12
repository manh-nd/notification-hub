package com.example.notificationhub.application.command;

import com.example.notificationhub.domain.common.IdempotencyKey;
import com.example.notificationhub.domain.common.TenantId;
import com.example.notificationhub.domain.consent.Topic;
import com.example.notificationhub.domain.target.ContactProfile;
import java.util.Map;

public record SendNotificationCommand(
        TenantId tenantId,
        IdempotencyKey idempotencyKey,
        String userId,
        Topic topic,
        String templateCode,
        ContactProfile contactProfile,
        Map<String, Object> facts,
        Map<String, Object> payload
) {
}
