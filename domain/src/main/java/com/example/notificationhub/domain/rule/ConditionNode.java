package com.example.notificationhub.domain.rule;

import java.util.Map;
import java.util.Objects;

public record ConditionNode(String factKey, Operator operator, String expectedValue) implements RuleNode {
    public enum Operator { EQUALS, NOT_EQUALS, CONTAINS, IN }

    public ConditionNode {
        if (factKey == null || factKey.isBlank()) {
            throw new IllegalArgumentException("factKey must not be blank");
        }
        if (operator == null) {
            throw new IllegalArgumentException("operator must not be null");
        }
    }

    @Override
    public boolean evaluate(Map<String, Object> facts) {
        Object actual = facts.get(factKey);
        return switch (operator) {
            case EQUALS -> Objects.toString(actual, null) != null && Objects.toString(actual).equals(expectedValue);
            case NOT_EQUALS -> !Objects.equals(Objects.toString(actual, null), expectedValue);
            case CONTAINS -> Objects.toString(actual, "").contains(expectedValue);
            case IN -> expectedValue != null && actual != null && expectedValue.contains(Objects.toString(actual));
        };
    }
}
