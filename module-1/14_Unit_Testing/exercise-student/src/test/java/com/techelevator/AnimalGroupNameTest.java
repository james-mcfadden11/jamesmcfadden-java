package com.techelevator;

import org.junit.*;

public class AnimalGroupNameTest {
    private AnimalGroupName sut;

    /*
    test cases:
    all lower case
    capitalized first letter
    random capital/lower case letters
    null
    empty
    valid string but not in map
    */

    @Before
    public void setup() {
        sut = new AnimalGroupName();
    }

    // all lower case
    @Test
    public void getHerd_returns_Crash_given_rhino() {
        // arrange
        // act
        String actual = sut.getHerd("rhino");
        // assert
        Assert.assertEquals("Crash", actual);
    }

    // capitalized first letter
    @Test
    public void getHerd_returns_Crash_given_Rhino() {
        // arrange
        // act
        String actual = sut.getHerd("Rhino");
        // assert
        Assert.assertEquals("Crash", actual);
    }

    // random capital/lower case letters
    @Test
    public void getHerd_returns_Crash_given_rHiNo() {
        // arrange
        // act
        String actual = sut.getHerd("rHiNo");
        // assert
        Assert.assertEquals("Crash", actual);
    }

    // null
    @Test
    public void getHerd_returns_unknown_given_null() {
        // arrange
        // act
        String actual = sut.getHerd(null);
        // assert
        Assert.assertEquals("unknown", actual);
    }

    // empty
    @Test
    public void getHerd_returns_unknown_given_empty() {
        // arrange
        // act
        String actual = sut.getHerd("");
        // assert
        Assert.assertEquals("unknown", actual);
    }

    // valid string but not in map
    @Test
    public void getHerd_returns_unknown_given_word_not_in_map() {
        // arrange
        // act
        String actual = sut.getHerd("iguana");
        // assert
        Assert.assertEquals("unknown", actual);
    }





}
