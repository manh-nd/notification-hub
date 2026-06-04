package com.example.notificationhub.domain.rule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.notificationhub.domain.common.TenantId;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class DynamicRuleTest {

    @Test
    void routeBranchRejectsInvalidValues() {
        assertThrows(IllegalArgumentException.class, () -> new RouteBranch(null, "EMAIL"));
        assertThrows(IllegalArgumentException.class,
                () -> new RouteBranch(new ConditionNode("channel", ConditionNode.Operator.EQUALS, "SMS"), " "));
    }

    @Test
    void andNodeAndOrNodeRequireValidChildren() {
        assertThrows(IllegalArgumentException.class, () -> new AndNode(null));
        assertThrows(IllegalArgumentException.class, () -> new OrNode(null));
        assertThrows(IllegalArgumentException.class, () -> new AndNode(List.of()));
        assertThrows(IllegalArgumentException.class, () -> new OrNode(List.of()));
        assertThrows(IllegalArgumentException.class,
                () -> new AndNode(java.util.Arrays.asList(new ConditionNode("a", ConditionNode.Operator.EQUALS, "1"), null)));
        assertThrows(IllegalArgumentException.class,
                () -> new OrNode(java.util.Arrays.asList(new ConditionNode("a", ConditionNode.Operator.EQUALS, "1"), null)));
    }

    @Test
    void dynamicRuleReturnsFirstMatchingBranch() {
        RouteBranch first = new RouteBranch(new ConditionNode("priority", ConditionNode.Operator.EQUALS, "HIGH"), "SMS");
        RouteBranch second = new RouteBranch(new ConditionNode("priority", ConditionNode.Operator.CONTAINS, "H"), "EMAIL");

        DynamicRule rule = new DynamicRule(new TenantId("tenant-1"), List.of(first, second), "PUSH");

        assertEquals("SMS", rule.evaluateRouting(Map.of("priority", "HIGH")));
    }

    @Test
    void dynamicRuleReturnsDefaultWhenNoBranchMatches() {
        RouteBranch sms = new RouteBranch(new ConditionNode("priority", ConditionNode.Operator.EQUALS, "HIGH"), "SMS");

        DynamicRule rule = new DynamicRule(new TenantId("tenant-1"), List.of(sms), "PUSH");

        assertEquals("PUSH", rule.evaluateRouting(Map.of("priority", "LOW")));
    }

    @Test
    void dynamicRuleRejectsInvalidConstructionAndFacts() {
        RouteBranch sms = new RouteBranch(new ConditionNode("priority", ConditionNode.Operator.EQUALS, "HIGH"), "SMS");

        assertThrows(IllegalArgumentException.class, () -> new DynamicRule(null, List.of(sms), "PUSH"));
        assertThrows(IllegalArgumentException.class, () -> new DynamicRule(new TenantId("tenant-1"), null, "PUSH"));
        assertThrows(IllegalArgumentException.class, () -> new DynamicRule(new TenantId("tenant-1"), java.util.Arrays.asList(sms, null), "PUSH"));
        assertThrows(IllegalArgumentException.class, () -> new DynamicRule(new TenantId("tenant-1"), List.of(sms), " "));

        DynamicRule rule = new DynamicRule(new TenantId("tenant-1"), List.of(sms), "PUSH");
        assertThrows(IllegalArgumentException.class, () -> rule.evaluateRouting(null));
    }
}
