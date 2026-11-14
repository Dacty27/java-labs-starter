package edu.course.lab04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IssueIdTest {

    @Test
    void logicalEqualityByProjectAndNumber() {
        IssueId a = new IssueId("CORE", 42);
        IssueId b = new IssueId("CORE", 42);
        IssueId c = new IssueId("CORE", 43);
        IssueId d = new IssueId("WEB",  42);

        assertNotSame(a, b);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());

        assertNotEquals(a, c);
        assertNotEquals(a, d);
    }

    @Test
    void comparisonWithNullAndOtherTypeReturnsFalse() {
        IssueId a = new IssueId("CORE", 1);
        assertFalse(a.equals(null));
        assertFalse(a.equals("CORE-1"));
        assertFalse(a.equals(new Book("CORE-1", "x")));
    }

    @Test
    void invalidArgumentsRejected() {
        assertThrows(IllegalArgumentException.class, () -> new IssueId(null, 1));
        assertThrows(IllegalArgumentException.class, () -> new IssueId(" ", 1));
        assertThrows(IllegalArgumentException.class, () -> new IssueId("CORE", 0));
    }
}