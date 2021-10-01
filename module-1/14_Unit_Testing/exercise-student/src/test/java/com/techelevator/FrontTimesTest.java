package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FrontTimesTest {
    private FrontTimes sut;

    /*
    test cases:
        empty string
        null
        length less than 3
        length 3 or more
    */

    @Before
    public void setup() {
        sut = new FrontTimes();
    }

    // empty string
    @Test
    public void generateString_returns_empty_string_given_empty_string() {
        // arrange
        // act
        String resultStr = sut.generateString("", 2);
        // assert
        Assert.assertEquals("", resultStr);
    }

    // null
    @Test
    public void generateString_returns_empty_string_given_null() {
        // arrange
        // act
        String resultStr = sut.generateString(null, 2);
        // assert
        Assert.assertEquals("", resultStr);
    }

    // length less than 3
    @Test
    public void generateString_returns_JMJMJM_given_JM_times3() {
        // arrange
        // act
        String resultStr = sut.generateString("JM", 3);
        // assert
        Assert.assertEquals("JMJMJM", resultStr);
    }

    // length 3 or more
    @Test
    public void generateString_returns_jimjimjim_string_given_jimbo_times3() {
        // arrange
        // act
        String resultStr = sut.generateString("jimbo", 3);
        // assert
        Assert.assertEquals("jimjimjim", resultStr);
    }

}
