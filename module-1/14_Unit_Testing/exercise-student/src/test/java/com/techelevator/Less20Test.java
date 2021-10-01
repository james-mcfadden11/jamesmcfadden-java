package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Less20Test {
    private Less20 sut;

    /*
    test cases:
        0
        18
        20
        15
    */

    @Before
    public void setup() {
        sut = new Less20();
    }

    // 0
    @Test
    public void isLessThanMultipleOf20_returns_false_given_0() {
        // arrange
        // act
        boolean result = sut.isLessThanMultipleOf20(0);
        // assert
        Assert.assertFalse(result);
    }

    // 20
    @Test
    public void isLessThanMultipleOf20_returns_false_given_20() {
        // arrange
        // act
        boolean result = sut.isLessThanMultipleOf20(20);
        // assert
        Assert.assertFalse(result);
    }

    // 15
    @Test
    public void isLessThanMultipleOf20_returns_false_given_15() {
        // arrange
        // act
        boolean result = sut.isLessThanMultipleOf20(15);
        // assert
        Assert.assertFalse(result);
    }

    // 18
    @Test
    public void isLessThanMultipleOf20_returns_true_given_18() {
        // arrange
        // act
        boolean result = sut.isLessThanMultipleOf20(18);
        // assert
        Assert.assertTrue(result);
    }

}
