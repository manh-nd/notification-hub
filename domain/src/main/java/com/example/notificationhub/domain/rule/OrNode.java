package com.example.notificationhub.domain.rule;

import java.util.List;
import java.util.Map;

public record OrNode(List<RuleNode> children) implements RuleNode {
    public OrNode {
        children = List.copyOf(children);
        if (children.isEmpty()) {
            throw new IllegalArgumentException("or node requires at least one child");
        }
    }

    @Override
    public boolean evaluate(Map<String, Object> facts) {
        return children.stream().anyMatch(node -> node.evaluate(facts));
    }
}
