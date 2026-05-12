package com.example.notificationhub.domain.port;

import com.example.notificationhub.domain.common.TenantId;
import com.example.notificationhub.domain.rule.DynamicRule;

public interface DynamicRuleRepository {
    DynamicRule getByTenantId(TenantId tenantId);
}
