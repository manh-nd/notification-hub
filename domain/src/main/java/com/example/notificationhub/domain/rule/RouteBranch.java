package com.example.notificationhub.domain.rule;

public record RouteBranch(RuleNode condition, String channelType) {
    public RouteBranch {
        if (condition == null) {
            throw new IllegalArgumentException("condition must not be null");
        }
        if (channelType == null || channelType.isBlank()) {
            throw new IllegalArgumentException("channelType must not be blank");
        }
    }
}
