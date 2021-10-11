package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class M1D06IntroObjectsStringsExerciseTest {
//    hasBad("badxx") → true
//    hasBad("xbadxx") → true
//    hasBad("xxbadxx") → false
    @Test
    public void hasBad_returns_true_given_badxx() {
        // arrange
        M1D06IntroObjectsStringsExercise sut = new M1D06IntroObjectsStringsExercise();
        // act
        boolean hasBad = sut.hasBad("badxx");
        // assert
        Assert.assertTrue(hasBad);
    }

    @Test
    public void hasBad_returns_true_given_xbadxx() {
        // arrange
        M1D06IntroObjectsStringsExercise sut = new M1D06IntroObjectsStringsExercise();
        // act
        boolean hasBad = sut.hasBad("xbadxx");
        // assert
        Assert.assertTrue(hasBad);
    }

    @Test
    public void hasBad_returns_false_given_xxbadxx() {
        // arrange
        M1D06IntroObjectsStringsExercise sut = new M1D06IntroObjectsStringsExercise();
        // act
        boolean hasBad = sut.hasBad("xxbadxx");
        // assert
        Assert.assertFalse(hasBad);
    }

    @Test
    public void hasBad_returns_false_given_null() {
        // arrange
        M1D06IntroObjectsStringsExercise sut = new M1D06IntroObjectsStringsExercise();
        // act
        boolean hasBad = sut.hasBad(null);
        // assert
        Assert.assertFalse(hasBad);
    }

    @Test
    public void hasBad_returns_false_given_empty() {
        // arrange
        M1D06IntroObjectsStringsExercise sut = new M1D06IntroObjectsStringsExercise();
        // act
        boolean hasBad = sut.hasBad("");
        // assert
        Assert.assertFalse(hasBad);
    }


}
