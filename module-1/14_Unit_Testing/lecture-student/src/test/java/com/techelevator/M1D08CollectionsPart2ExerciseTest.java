package com.techelevator;
import org.junit.*;

import java.util.HashMap;
import java.util.Map;

public class M1D08CollectionsPart2ExerciseTest {

//    wordCount(["ba", "ba", "black", "sheep"]) → {"ba" : 2, "black": 1, "sheep": 1 }

    @Test
    public void wordCount_returns_ba2_black1_sheep1() {
        // arrange
        M1D08CollectionsPart2Exercise sut = new M1D08CollectionsPart2Exercise();
        Map<String, Integer> expected = Map.of("ba", 2, "black", 1, "sheep", 1);
        // act
        Map<String, Integer> result = sut.wordCount(new String[] {"ba", "ba", "black", "sheep"});
        // assert
        Assert.assertEquals(expected, result);
    }

    @Test
    public void wordCount_returns_empty_map_given_null() {
        // arrange
        M1D08CollectionsPart2Exercise sut = new M1D08CollectionsPart2Exercise();
        Map<String, Integer> expected = new HashMap<>();
        // act
        Map<String, Integer> result = sut.wordCount(null);
        // assert
        Assert.assertEquals(null, result);

    }

}
