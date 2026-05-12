package com.example.notificationhub.domain.rule;

import com.example.notificationhub.domain.common.TenantId;
import java.util.List;
import java.util.Map;

public record DynamicRule(TenantId tenantId, List<RouteBranch> branches, String defaultChannelType) {
    public DynamicRule {
        if (tenantId == null) {
            throw new IllegalArgumentException("tenantId must not be null");
        }
        if (branches == null) {
            throw new IllegalArgumentException("branches must not be null");
        }
        if (branches.stream().anyMatch(branch -> branch == null)) {
            throw new IllegalArgumentException("branches must not contain null elements");
        }
        branches = List.copyOf(branches);
        if (defaultChannelType == null || defaultChannelType.isBlank()) {
            throw new IllegalArgumentException("defaultChannelType must not be blank");
        }
    }

    public String evaluateRouting(Map<String, Object> facts) {
        if (facts == null) {
            throw new IllegalArgumentException("facts must not be null");
        }
        return branches.stream()
                .filter(branch -> branch.condition().evaluate(facts))
                .map(RouteBranch::channelType)
                .findFirst()
                .orElse(defaultChannelType);
    }
}
