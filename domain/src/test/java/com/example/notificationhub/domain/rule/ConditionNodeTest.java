package com.example.notificationhub.domain.rule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import org.junit.jupiter.api.Test;

class ConditionNodeTest {

    @Test
    void inOperatorMatchesOnlyExactTokens() {
        ConditionNode node = new ConditionNode("channel", ConditionNode.Operator.IN, "EMAIL,SMS");

        assertFalse(node.evaluate(Map.of("channel", "MAIL")));
    }

    @Test
    void inOperatorMatchesExactTokenWithWhitespace() {
        ConditionNode node = new ConditionNode("channel", ConditionNode.Operator.IN, " EMAIL , SMS ");

        assertTrue(node.evaluate(Map.of("channel", "EMAIL")));
    }
}
