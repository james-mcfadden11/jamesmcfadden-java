package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NonStartTest {
    private NonStart sut;

    /*
    test cases:
        normal case, >1 character in each
        1 char in first string
        1 char in second string
        1 char in both strings
        a is empty
        b is empty
        both empty...fails
    */

    @Before
    public void setup() {
        sut = new NonStart();
    }

    // normal case, >1 character in each
    @Test
    public void getPartialString_returns_imob_given_jim_bob() {
        // arrange
        // act
        String result = sut.getPartialString("jim", "bob");
        // assert
        Assert.assertEquals("imob", result);
    }

    // 1 char in first string
    @Test
    public void getPartialString_returns_ob_given_j_bob() {
        // arrange
        // act
        String result = sut.getPartialString("j", "bob");
        // assert
        Assert.assertEquals("ob", result);
    }

    // 1 char in second string
    @Test
    public void getPartialString_returns_im_given_jim_b() {
        // arrange
        // act
        String result = sut.getPartialString("jim", "b");
        // assert
        Assert.assertEquals("im", result);
    }

    // 1 char in both strings
    @Test
    public void getPartialString_returns_empty_given_j_b() {
        // arrange
        // act
        String result = sut.getPartialString("j", "b");
        // assert
        Assert.assertEquals("", result);
    }

    // a is empty...not allowed given problem statement
    @Test
    public void getPartialString_returns_ob_given_empty_bob() {
        // arrange
        // act
        String result = sut.getPartialString("", "bob");
        // assert
        Assert.assertEquals("ob", result);
    }

    // b is empty...not allowed given problem statement
    @Test
    public void getPartialString_returns_im_given_jim_empty() {
        // arrange
        // act
        String result = sut.getPartialString("jim", "");
        // assert
        Assert.assertEquals("im", result);
    }

    // both empty...not allowed given problem statement
    @Test
    public void getPartialString_returns_empty_given_both_empty() {
        // arrange
        // act
        String result = sut.getPartialString("", "");
        // assert
        Assert.assertEquals("", result);
    }

    // both null...not allowed given problem statement
    @Test
    public void getPartialString_returns_empty_given_both_null() {
        // arrange
        // act
        String result = sut.getPartialString(null, null);
        // assert
        Assert.assertEquals("", result);
    }

}
