package edu.course.lab04;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class CollisionTest {

    final static class ConstantHashKey {
        final String value;
        ConstantHashKey(String value) { this.value = value; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            return value.equals(((ConstantHashKey) o).value);
        }

        @Override
        public int hashCode() { return 0; } // Намеренная коллизия
    }

    @Test
    void collidingKeysKeepDistinctValues() {
        ConstantHashKey k1 = new ConstantHashKey("A");
        ConstantHashKey k2 = new ConstantHashKey("B");

        // Предусловие эксперимента: хеши совпадают, но ключи неравны
        assertEquals(k1.hashCode(), k2.hashCode());
        assertNotEquals(k1, k2);

        // Проверка, что HashMap хранит оба значения
        Map<ConstantHashKey, String> map = new HashMap<>();
        map.put(k1, "value-1");
        map.put(k2, "value-2");

        assertEquals(2, map.size());
        assertEquals("value-1", map.get(k1));
        assertEquals("value-2", map.get(k2));
    }
}