package edu.course.lab04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChainedHashTableTest {

    @Test
    void basicOperationsWork() {
        ChainedHashTable table = new ChainedHashTable();
        IssueId id1 = new IssueId("CORE", 1);
        ProjectTask task1 = new ProjectTask(id1, "Fix bug", "alice");

        table.put(id1, task1);
        assertTrue(table.contains(id1));
        assertEquals(1, table.size());
        assertEquals("Fix bug", table.get(id1).orElseThrow().title());

        ProjectTask task2 = new ProjectTask(id1, "Fix bug (updated)", "bob");
        table.put(id1, task2);
        assertEquals(1, table.size());
        assertEquals("Fix bug (updated)", table.get(id1).orElseThrow().title());

        assertTrue(table.remove(id1).isPresent());
        assertFalse(table.contains(id1));
        assertEquals(0, table.size());
    }

    @Test
    void handlesCollisionsCorrectly() {
        ChainedHashTable table = new ChainedHashTable();


        for (int i = 1; i <= 100; i++) {
            IssueId id = new IssueId("CORE", i);
            table.put(id, new ProjectTask(id, "Task " + i, "dev"));
        }

        assertEquals(100, table.size());

        for (int i = 1; i <= 100; i++) {
            IssueId id = new IssueId("CORE", i);
            assertTrue(table.contains(id));
            assertEquals("Task " + i, table.get(id).orElseThrow().title());
        }
    }
}