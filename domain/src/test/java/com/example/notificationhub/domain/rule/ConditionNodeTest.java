package com.example.notificationhub.domain.rule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import org.junit.jupiter.api.Test;

class ConditionNodeTest {

    @Test
    void constructorRejectsBlankFactKey() {
        assertThrows(IllegalArgumentException.class,
                () -> new ConditionNode(" ", ConditionNode.Operator.EQUALS, "EMAIL"));
    }

    @Test
    void constructorRejectsNullOperator() {
        assertThrows(IllegalArgumentException.class,
                () -> new ConditionNode("channel", null, "EMAIL"));
    }

    @Test
    void equalsOperatorMatchesExactValue() {
        ConditionNode node = new ConditionNode("channel", ConditionNode.Operator.EQUALS, "EMAIL");

        assertTrue(node.evaluate(Map.of("channel", "EMAIL")));
        assertFalse(node.evaluate(Map.of("channel", "SMS")));
    }

    @Test
    void notEqualsOperatorHandlesMissingFacts() {
        ConditionNode node = new ConditionNode("channel", ConditionNode.Operator.NOT_EQUALS, "EMAIL");

        assertTrue(node.evaluate(Map.of()));
        assertFalse(node.evaluate(Map.of("channel", "EMAIL")));
    }

    @Test
    void containsOperatorUsesStringContains() {
        ConditionNode node = new ConditionNode("message", ConditionNode.Operator.CONTAINS, "urgent");

        assertTrue(node.evaluate(Map.of("message", "This is urgent")));
        assertFalse(node.evaluate(Map.of("message", "This is normal")));
    }

    @Test
    void containsOperatorReturnsFalseWhenExpectedValueIsNull() {
        ConditionNode node = new ConditionNode("message", ConditionNode.Operator.CONTAINS, null);

        assertFalse(node.evaluate(Map.of("message", "This is urgent")));
    }

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

    @Test
    void inOperatorRequiresBothExpectedAndActualValues() {
        ConditionNode nullExpected = new ConditionNode("channel", ConditionNode.Operator.IN, null);
        ConditionNode missingActual = new ConditionNode("channel", ConditionNode.Operator.IN, "EMAIL");

        assertFalse(nullExpected.evaluate(Map.of("channel", "EMAIL")));
        assertFalse(missingActual.evaluate(Map.of()));
    }

    @Test
    void evaluateRejectsNullFacts() {
        ConditionNode node = new ConditionNode("channel", ConditionNode.Operator.EQUALS, "EMAIL");

        assertThrows(IllegalArgumentException.class, () -> node.evaluate(null));
    }
}
