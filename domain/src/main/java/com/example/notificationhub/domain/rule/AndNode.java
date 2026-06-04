package com.example.notificationhub.domain.rule;

import java.util.List;
import java.util.Map;

public record AndNode(List<RuleNode> children) implements RuleNode {
    public AndNode {
        if (children == null) {
            throw new IllegalArgumentException("children must not be null");
        }
        if (children.stream().anyMatch(child -> child == null)) {
            throw new IllegalArgumentException("children must not contain null elements");
        }
        children = List.copyOf(children);
        if (children.isEmpty()) {
            throw new IllegalArgumentException("and node requires at least one child");
        }
    }

    @Override
    public boolean evaluate(Map<String, Object> facts) {
        if (facts == null) {
            throw new IllegalArgumentException("facts must not be null");
        }
        return children.stream().allMatch(node -> node.evaluate(facts));
    }
}
