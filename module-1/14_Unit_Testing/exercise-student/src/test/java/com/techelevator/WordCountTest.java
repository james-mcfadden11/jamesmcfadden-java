package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class WordCountTest {
    private WordCount sut;

    /*
    test cases:
        null array
        array with null Strings
        valid input with no repeats
        valid input with some repeats
    */

    @Before
    public void setup() {
        sut = new WordCount();
    }

    // null
    @Test
    public void getCount_returns_empty_map_given_null() {
        // arrange
        // act
        Map<String, Integer> result = sut.getCount(null);
        Map<String, Integer> expected = new HashMap<>();
//        expected.put();
        // assert
        Assert.assertEquals(expected, result);
    }

    // array with null Strings
    @Test
    public void getCount_returns_empty_map_given_null_strings() {
        // arrange
        // act
        Map<String, Integer> result = sut.getCount(new String[] {null, null, null});
        // not sure what this SHOULD return, doesn't mention it in problem statement
        // assert
        Map<String, Integer> expected = new HashMap<>();
//        expected.put();
        Assert.assertEquals(expected, result);
    }

    // valid input with no repeats
    @Test
    public void getCount_returns_a1b1c1_map_given_abc() {
        // arrange
        // act
        Map<String, Integer> result = sut.getCount(new String[] {"a", "b", "c"});
        // assert
        Map<String, Integer> expected = new HashMap<>();
        expected.put("a", 1);
        expected.put("b", 1);
        expected.put("c", 1);
        Assert.assertEquals(expected, result);
    }

    //
    @Test
    public void getCount_returns_a2b2c1d1_map_given_aabcbd() {
        // arrange
        // act
        Map<String, Integer> result = sut.getCount(new String[] {"a", "a", "b", "c", "b", "d"});
        // assert
        Map<String, Integer> expected = new HashMap<>();
        expected.put("a", 2);
        expected.put("b", 2);
        expected.put("c", 1);
        expected.put("d", 1);
        Assert.assertEquals(expected, result);
    }

}
