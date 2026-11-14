package edu.course.lab03;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StatusPriorityPolicyTest {

    @Test
    void newTaskHasHighestStatusPriority() {
        PriorityPolicy policy = new StatusPriorityPolicy();
        ProjectTask newTask = new ProjectTask("A", 1, "NEW");
        ProjectTask done    = new ProjectTask("B", 1, "DONE");
        assertTrue(policy.calculatePriority(newTask) > policy.calculatePriority(done));
    }

    @Test
    void sameStatusYieldsSamePriorityRegardlessOfEffort() {
        PriorityPolicy policy = new StatusPriorityPolicy();
        ProjectTask a = new ProjectTask("A", 1, "IN_PROGRESS");
        ProjectTask b = new ProjectTask("B", 99, "IN_PROGRESS");
        assertEquals(policy.calculatePriority(a), policy.calculatePriority(b));
    }
}