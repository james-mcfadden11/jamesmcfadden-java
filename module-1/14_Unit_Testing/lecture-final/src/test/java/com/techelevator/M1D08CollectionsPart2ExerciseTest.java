package com.techelevator;

import org.junit.*;

import java.util.HashMap;
import java.util.Map;

public class M1D08CollectionsPart2ExerciseTest {

    /*
    wordCount(["ba", "ba", "black", "sheep"]) → {"ba" : 2, "black": 1, "sheep": 1 }
     */

    @Test
    public void wordCount_returns_ba2_black1_sheep1() {
        // Arrange
        M1D08CollectionsPart2Exercise sut = new M1D08CollectionsPart2Exercise();
        Map<String, Integer> expected = Map.of("ba", 2, "black", 1, "sheep", 1);
//        Map<String, Integer> expected = new HashMap<>();
//        expected.put("ba", 2);
//        expected.put("black", 1);
//        expected.put("sheep", 1);


        // Act
        Map<String, Integer> result = sut.wordCount(new String[] { "ba", "ba", "black", "sheep" });

        // Assert
        Assert.assertEquals(expected, result);
    }

    @Test
    public void wordCount_returns_null_given_null() {
        // Arrange
        M1D08CollectionsPart2Exercise sut = new M1D08CollectionsPart2Exercise();
//        Map<String, Integer> expected = Map.of("ba", 2, "black", 1, "sheep", 1);
//        Map<String, Integer> expected = new HashMap<>();


        // Act
        Map<String, Integer> result = sut.wordCount(null);

        // Assert
        Assert.assertNull(result);
    }
}
