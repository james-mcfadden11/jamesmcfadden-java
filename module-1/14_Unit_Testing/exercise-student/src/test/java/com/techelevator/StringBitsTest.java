package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringBitsTest {
    private StringBits sut;

    /*
    test cases:
        empty string
        null
        length of 1
        length > 1
    */

    @Before
    public void setup() {
        sut = new StringBits();
    }

    // empty string
    @Test
    public void getBits_returns_empty_given_empty() {
        // arrange
        // act
        String result = sut.getBits("");
        // assert
        Assert.assertEquals("", result);
    }

    // null
    @Test
    public void getBits_returns_empty_given_null() {
        // arrange
        // act
        String result = sut.getBits(null);
        // assert
        Assert.assertEquals("", result);
    }

    // length 1
    @Test
    public void getBits_returns_J_given_J() {
        // arrange
        // act
        String result = sut.getBits("J");
        // assert
        Assert.assertEquals("J", result);
    }

    // length > 1
    @Test
    public void getBits_returns_Jmo_given_Jimbo() {
        // arrange
        // act
        String result = sut.getBits("Jimbo");
        // assert
        Assert.assertEquals("Jmo", result);
    }

}
