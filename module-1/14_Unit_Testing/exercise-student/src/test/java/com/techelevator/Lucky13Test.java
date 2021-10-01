package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Lucky13Test {
    private Lucky13 sut;

    /*
    test cases:
        null
        empty
        contains a 1
        contains a 3
        contains no 1's or 3's
    */

    @Before
    public void setup() {
        sut = new Lucky13();
    }

    // null
    @Test
    public void getLucky_returns_true_given_null() {
        // arrange
        // act
        boolean result = sut.getLucky(null);
        // assert
        Assert.assertTrue(result);
    }

    // empty array
    @Test
    public void getLucky_returns_true_given_empty_array() {
        // arrange
        // act
        boolean result = sut.getLucky(new int[] {});
        // assert
        Assert.assertTrue(result);
    }

    // contains a 1
    @Test
    public void getLucky_returns_false_given_contains_a_1() {
        // arrange
        // act
        boolean result = sut.getLucky(new int[] {5, 7, 1});
        // assert
        Assert.assertFalse(result);
    }

    // contains a 3
    @Test
    public void getLucky_returns_false_given_contains_a_3() {
        // arrange
        // act
        boolean result = sut.getLucky(new int[] {4, 3, 9});
        // assert
        Assert.assertFalse(result);
    }

    // no 1's or 3's
    @Test
    public void getLucky_returns_true_given_no_1s_or_3s() {
        // arrange
        // act
        boolean result = sut.getLucky(new int[] {4, 5, 6, 7, 8});
        // assert
        Assert.assertTrue(result);
    }

}
