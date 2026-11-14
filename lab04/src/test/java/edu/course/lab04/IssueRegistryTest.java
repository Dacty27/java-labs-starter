package edu.course.lab04;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class IssueRegistryTest {

    private static ProjectTask task(IssueId id, String title) {
        return new ProjectTask(id, title, "alice");
    }

    @Test
    void equalButDifferentKeyInstancesFindSameValue() {
        IssueId stored = new IssueId("CORE", 1);
        IssueId lookup = new IssueId("CORE", 1); // другой экземпляр, тот же ISBN-аналог

        IssueRegistry registry = new IssueRegistry();
        registry.put(stored, task(stored, "Fix bug"));

        Optional<ProjectTask> found = registry.get(lookup);
        assertTrue(found.isPresent());
        assertEquals("Fix bug", found.get().title());
    }

    @Test
    void repeatedPutWithEqualKeyReplacesValueAndKeepsSize() {
        IssueId id = new IssueId("CORE", 1);
        IssueId sameId = new IssueId("CORE", 1);

        IssueRegistry registry = new IssueRegistry();
        registry.put(id, task(id, "First"));
        assertEquals(1, registry.size());

        Optional<ProjectTask> replaced = registry.put(sameId, task(sameId, "Second"));
        assertTrue(replaced.isPresent());
        assertEquals("First", replaced.get().title());
        assertEquals(1, registry.size());
        assertEquals("Second", registry.get(id).orElseThrow().title());
    }

    @Test
    void removeAndContains() {
        IssueId id = new IssueId("WEB", 7);
        IssueRegistry registry = new IssueRegistry();
        registry.put(id, task(id, "Do it"));

        assertTrue(registry.contains(id));
        assertTrue(registry.remove(id).isPresent());
        assertFalse(registry.contains(id));
        assertEquals(0, registry.size());
    }

    @Test
    void putRejectsMismatchedKey() {
        IssueRegistry registry = new IssueRegistry();
        IssueId key = new IssueId("CORE", 1);
        ProjectTask other = task(new IssueId("CORE", 2), "x");
        assertThrows(IllegalArgumentException.class, () -> registry.put(key, other));
    }
}