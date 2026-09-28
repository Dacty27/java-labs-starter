package edu.course.lab01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CourseToolkitTest {

    @Test
    void returnsTrueForEvenNumber() {
        assertTrue(CourseToolkit.isEven(8));
    }

    @Test
    void returnsFalseForOddNumber() {
        assertFalse(CourseToolkit.isEven(7));
    }

    @Test
    void returnsTrueForZero() {
        assertTrue(CourseToolkit.isEven(0));
    }

    @Test
    void returnsTrueForNegativeEvenNumber() {
        assertTrue(CourseToolkit.isEven(-8));
    }

    @Test
    void isPrimeReturnsFalseForNumbersLessThanTwo() {
        assertFalse(CourseToolkit.isPrime(0));
        assertFalse(CourseToolkit.isPrime(1));
        assertFalse(CourseToolkit.isPrime(-5));
    }

    @Test
    void isPrimeReturnsTrueForTwo() {
        assertTrue(CourseToolkit.isPrime(2));
    }

    @Test
    void isPrimeReturnsFalseForCompositeNumbers() {
        assertFalse(CourseToolkit.isPrime(4));
        assertFalse(CourseToolkit.isPrime(6));
        assertFalse(CourseToolkit.isPrime(100));
    }

    @Test
    void isPrimeReturnsTrueForPrimes() {
        assertTrue(CourseToolkit.isPrime(3));
        assertTrue(CourseToolkit.isPrime(13));
        assertTrue(CourseToolkit.isPrime(97));
    }

    @Test
    void isPrimeHandlesSquaresOfPrimes() {
        assertFalse(CourseToolkit.isPrime(9));
        assertFalse(CourseToolkit.isPrime(25));
        assertFalse(CourseToolkit.isPrime(49));
    }

    @Test
    void isPalindromeReturnsTrueForPalindrome() {
        assertTrue(CourseToolkit.isPalindrome("level"));
        assertTrue(CourseToolkit.isPalindrome("шалаш"));
    }

    @Test
    void isPalindromeIsCaseSensitive() {
        assertFalse(CourseToolkit.isPalindrome("Level"));
    }

    @Test
    void isPalindromeIsSpaceSensitive() {
        assertFalse(CourseToolkit.isPalindrome("level "));
    }

    @Test
    void isPalindromeThrowsForNull() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.isPalindrome(null));
    }

    @Test
    void averageReturnsFractionalResult() {
        assertEquals(2.5, CourseToolkit.average(new int[]{1, 2, 3, 4}), 0.0001);
    }

    @Test
    void averageHandlesNegativeNumbers() {
        assertEquals(-2.0, CourseToolkit.average(new int[]{-1, -2, -3}), 0.0001);
    }

    @Test
    void averageDoesNotModifyArray() {
        int[] values = {1, 2, 3};
        CourseToolkit.average(values);
        assertArrayEquals(new int[]{1, 2, 3}, values);
    }

    @Test
    void averageThrowsForNull() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.average(null));
    }

    @Test
    void averageThrowsForEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.average(new int[]{}));
    }

    @Test
    void minReturnsSmallestValue() {
        assertEquals(1, CourseToolkit.min(new int[]{3, 1, 4, 1, 5, 9, 2, 6}));
    }

    @Test
    void minHandlesNegativeNumbers() {
        assertEquals(-5, CourseToolkit.min(new int[]{3, -5, 0, 7}));
    }

    @Test
    void minThrowsForEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.min(new int[]{}));
    }

    @Test
    void maxReturnsLargestValue() {
        assertEquals(9, CourseToolkit.max(new int[]{3, 1, 4, 1, 5, 9, 2, 6}));
    }

    @Test
    void maxHandlesNegativeNumbers() {
        assertEquals(-1, CourseToolkit.max(new int[]{-3, -5, -1, -7}));
    }

    @Test
    void maxThrowsForEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.max(new int[]{}));
    }
}