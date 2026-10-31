package edu.course.lab03;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiscountTest {

    @Test
    void noDiscountReturnsOriginalPrice() {
        DiscountPolicy policy = new NoDiscount();
        assertEquals(100.0, policy.apply(100.0), 0.001);
    }

    @Test
    void percentDiscountAppliesCorrectly() {
        DiscountPolicy policy = new PercentDiscount(20);
        assertEquals(80.0, policy.apply(100.0), 0.001);
    }

    @Test
    void priceCalculatorSwapsPolicies() {
        PriceCalculator noDiscCalc = new PriceCalculator(new NoDiscount());
        assertEquals(100.0, noDiscCalc.calculate(100.0), 0.001);

        PriceCalculator percentCalc = new PriceCalculator(new PercentDiscount(20));
        assertEquals(80.0, percentCalc.calculate(100.0), 0.001);
    }

    @Test
    void negativePriceThrowsException() {
        DiscountPolicy policy = new PercentDiscount(20);
        assertThrows(IllegalArgumentException.class, () -> policy.apply(-50.0));
    }
}