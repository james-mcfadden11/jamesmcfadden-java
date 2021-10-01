package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SameFirstLastTest {
    private SameFirstLast sut;

    /*
    test cases:
        empty array
        null
        first != last
        array length of one
        first == last
    */

    @Before
    public void setup() {
        sut = new SameFirstLast();
    }

    // empty array
    @Test
    public void isItTheSame_returns_false_given_empty() {
        // arrange
        // act
        boolean result = sut.isItTheSame(new int[] {});
        // assert
        Assert.assertFalse(result);
    }

    // null
    @Test
    public void isItTheSame_returns_false_given_null() {
        // arrange
        // act
        boolean result = sut.isItTheSame(null);
        // assert
        Assert.assertFalse(result);
    }

    // first != last
    @Test
    public void isItTheSame_returns_false_given_1234() {
        // arrange
        // act
        boolean result = sut.isItTheSame(new int[] {1,2,3,4});
        // assert
        Assert.assertFalse(result);
    }

    // array length of one
    @Test
    public void isItTheSame_returns_false_given_length_of_1() {
        // arrange
        // act
        boolean result = sut.isItTheSame(new int[] {2});
        // assert
        Assert.assertTrue(result);
    }

    // first == last
    @Test
    public void isItTheSame_returns_true_given_12341() {
        // arrange
        // act
        boolean result = sut.isItTheSame(new int[] {1,2,3,4,1});
        // assert
        Assert.assertTrue(result);
    }


}
