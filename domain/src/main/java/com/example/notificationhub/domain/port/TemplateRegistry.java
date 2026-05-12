package com.example.notificationhub.domain.port;

import com.example.notificationhub.domain.common.TenantId;

public interface TemplateRegistry {
    String getTemplate(TenantId tenantId, String templateCode, String channelType);
}
