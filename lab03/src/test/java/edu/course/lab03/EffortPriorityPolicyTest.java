package edu.course.lab03;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EffortPriorityPolicyTest {

    @Test
    void returnsEffortAsPriority() {
        PriorityPolicy policy = new EffortPriorityPolicy();
        ProjectTask task = new ProjectTask("T", 5, "NEW");
        assertEquals(5, policy.calculatePriority(task));
    }

    @Test
    void largerEffortYieldsHigherPriority() {
        PriorityPolicy policy = new EffortPriorityPolicy();
        ProjectTask small = new ProjectTask("A", 2, "NEW");
        ProjectTask big   = new ProjectTask("B", 8, "NEW");
        assertTrue(policy.calculatePriority(big) > policy.calculatePriority(small));
    }
}