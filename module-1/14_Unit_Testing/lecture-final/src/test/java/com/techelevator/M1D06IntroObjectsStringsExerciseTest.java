package com.techelevator;

import org.junit.*;

public class M1D06IntroObjectsStringsExerciseTest {

    /*

     hasBad("badxx") → true
	 hasBad("xbadxx") → true
	 hasBad("xxbadxx") → false

     */

    @Test
    public void hasBad_returns_true_given_badxx() {
        // Arrange
        M1D06IntroObjectsStringsExercise sut = new M1D06IntroObjectsStringsExercise();

        // Act
        boolean hasBad = sut.hasBad("badxx");

        // Assert
        Assert.assertTrue(hasBad);
    }

    @Test
    public void hasBad_returns_true_given_xbadxx() {
        // Arrange
        M1D06IntroObjectsStringsExercise sut = new M1D06IntroObjectsStringsExercise();

        // Act
        boolean hasBad = sut.hasBad("xbadxx");

        // Assert
        Assert.assertTrue(hasBad);
    }

    @Test
    public void hasBad_returns_false_given_xxbadxx() {
        // Arrange
        M1D06IntroObjectsStringsExercise sut = new M1D06IntroObjectsStringsExercise();

        // Act
        boolean hasBad = sut.hasBad("xxbadxx");

        // Assert
        Assert.assertFalse(hasBad);
    }

    @Test
    public void hasBad_returns_false_given_null() {
        // Arrange
        M1D06IntroObjectsStringsExercise sut = new M1D06IntroObjectsStringsExercise();

        // Act
        boolean hasBad = sut.hasBad(null);

        // Assert
        Assert.assertFalse(hasBad);
    }

    @Test
    public void hasBad_returns_false_given_empty() {
        // Arrange
        M1D06IntroObjectsStringsExercise sut = new M1D06IntroObjectsStringsExercise();

        // Act
        boolean hasBad = sut.hasBad("");

        // Assert
        Assert.assertFalse(hasBad);
    }
}
