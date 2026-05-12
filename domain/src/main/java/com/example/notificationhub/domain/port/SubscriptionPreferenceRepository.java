package com.example.notificationhub.domain.port;

import com.example.notificationhub.domain.consent.SubscriptionPreference;
import com.example.notificationhub.domain.consent.Topic;
import com.example.notificationhub.domain.common.TenantId;

public interface SubscriptionPreferenceRepository {
    SubscriptionPreference getForUser(TenantId tenantId, String userId, Topic topic);
}
