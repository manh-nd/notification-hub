package com.example.notificationhub.domain.rule;

import java.util.List;
import java.util.Map;

public record OrNode(List<RuleNode> children) implements RuleNode {
    public OrNode {
        if (children == null) {
            throw new IllegalArgumentException("children must not be null");
        }
        if (children.stream().anyMatch(child -> child == null)) {
            throw new IllegalArgumentException("children must not contain null elements");
        }
        children = List.copyOf(children);
        if (children.isEmpty()) {
            throw new IllegalArgumentException("or node requires at least one child");
        }
    }

    @Override
    public boolean evaluate(Map<String, Object> facts) {
        if (facts == null) {
            throw new IllegalArgumentException("facts must not be null");
        }
        return children.stream().anyMatch(node -> node.evaluate(facts));
    }
}
