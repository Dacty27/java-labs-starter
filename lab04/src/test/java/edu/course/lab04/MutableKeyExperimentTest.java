package edu.course.lab04;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MutableKeyExperimentTest {

    @Test
    void mutatingKeyAfterInsertBreaksLookup() {
        MutableIssueKey key = new MutableIssueKey("CORE", 1);

        Map<MutableIssueKey, String> map = new HashMap<>();
        map.put(key, "value");
        assertEquals("value", map.get(key));

        key.setNumber(99);

        assertNull(map.get(key));          // поиск по «новому» ключу не находит
        assertEquals(1, map.size());       // но запись физически всё ещё в map

        key.setNumber(1);
        assertEquals("value", map.get(key));
    }
}