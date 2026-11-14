package edu.course.lab03;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskPrioritizerTest {

    @Test
    void effortPolicyPicksLargerEffort() {
        TaskPrioritizer prioritizer = new TaskPrioritizer(new EffortPriorityPolicy());
        ProjectTask small = new ProjectTask("A", 2, "NEW");
        ProjectTask big   = new ProjectTask("B", 8, "DONE");
        assertSame(big, prioritizer.pickHigher(small, big));
    }

    @Test
    void swappingPolicyChangesResultWithoutChangingPrioritizerClass() {
        ProjectTask small = new ProjectTask("A", 2, "NEW");
        ProjectTask big   = new ProjectTask("B", 8, "DONE");

        TaskPrioritizer byEffort = new TaskPrioritizer(new EffortPriorityPolicy());
        assertSame(big, byEffort.pickHigher(small, big));

        TaskPrioritizer byStatus = new TaskPrioritizer(new StatusPriorityPolicy());
        assertSame(small, byStatus.pickHigher(small, big));
    }

    @Test
    void inputsAreNotModified() {
        TaskPrioritizer prioritizer = new TaskPrioritizer(new EffortPriorityPolicy());
        ProjectTask a = new ProjectTask("A", 2, "NEW");
        ProjectTask b = new ProjectTask("B", 8, "DONE");
        prioritizer.pickHigher(a, b);
        assertEquals("A", a.getTitle());
        assertEquals(2, a.getEffort());
        assertEquals("NEW", a.getStatus());
        assertEquals("B", b.getTitle());
        assertEquals(8, b.getEffort());
        assertEquals("DONE", b.getStatus());
    }
}