package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MaxEnd3Test {
    private MaxEnd3 sut;

    /*
    test cases:
        first num larger
        last num larger
        negative numbers
    */

    @Before
    public void setup() {
        sut = new MaxEnd3();
    }

    // first num larger
    @Test
    public void makeArray_returns_333_given_321() {
        // arrange
        // act
        int[] result = sut.makeArray(new int[] {3,2,1});
        // assert
        int[] expected = new int[] {3,3,3};
        Assert.assertArrayEquals(expected, result);
    }

    // last num larger
    @Test
    public void makeArray_returns_333_given_123() {
        // arrange
        // act
        int[] result = sut.makeArray(new int[] {1,2,3});
        // assert
        int[] expected = new int[] {3,3,3};
        Assert.assertArrayEquals(expected, result);
    }

    // negative numbers
    @Test
    public void makeArray_returns_neg1neg1neg1_given_neg3neg2neg1() {
        // arrange
        // act
        int[] result = sut.makeArray(new int[] {-3,-2,-1});
        // assert
        int[] expected = new int[] {-1,-1,-1};
        Assert.assertArrayEquals(expected, result);
    }

}
