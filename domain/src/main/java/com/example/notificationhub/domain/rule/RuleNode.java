package com.example.notificationhub.domain.rule;

import java.util.Map;

public sealed interface RuleNode permits AndNode, OrNode, ConditionNode {
    boolean evaluate(Map<String, Object> facts);
}
