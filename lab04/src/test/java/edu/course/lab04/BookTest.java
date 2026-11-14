package edu.course.lab04;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void equalBooksWithSameIsbnFindValueInHashMap() {
        Book key1 = new Book("978-3-16-148410-0", "Effective Java");
        Book key2 = new Book("978-3-16-148410-0", "Effective Java, 3rd ed.");

        Map<Book, String> map = new HashMap<>();
        map.put(key1, "value");

        // key1 и key2 — разные экземпляры, но логически равны
        assertNotSame(key1, key2);
        assertEquals(key1, key2);
        assertEquals(key1.hashCode(), key2.hashCode());
        assertEquals("value", map.get(key2));
    }

    @Test
    void booksWithDifferentIsbnAreNotEqual() {
        Book a = new Book("111", "A");
        Book b = new Book("222", "A");

        assertNotEquals(a, b);
    }
}