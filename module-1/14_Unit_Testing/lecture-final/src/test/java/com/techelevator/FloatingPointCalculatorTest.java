package com.techelevator;

import org.junit.*;

public class FloatingPointCalculatorTest {

    @Test
    public void add_returns_correct_value() {

        // Arrange
        FloatingPointCalculator calculator = new FloatingPointCalculator();

        // Act
        double actual = calculator.add(2.5, 3.4);

        // Assert
        Assert.assertEquals(5.9, actual, 0);
    }
}
