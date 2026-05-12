package com.example.notificationhub.domain.rule;

import java.util.List;
import java.util.Map;

public record AndNode(List<RuleNode> children) implements RuleNode {
    public AndNode {
        children = List.copyOf(children);
        if (children.isEmpty()) {
            throw new IllegalArgumentException("and node requires at least one child");
        }
    }

    @Override
    public boolean evaluate(Map<String, Object> facts) {
        return children.stream().allMatch(node -> node.evaluate(facts));
    }
}
