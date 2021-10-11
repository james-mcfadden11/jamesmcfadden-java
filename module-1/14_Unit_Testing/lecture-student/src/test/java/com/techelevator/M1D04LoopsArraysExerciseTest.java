package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class M1D04LoopsArraysExerciseTest {
    private M1D04LoopsArraysExercise sut;

    @Before
    public void setup() {
        sut = new M1D04LoopsArraysExercise();
    }

    @Test
    public void countEvens_returns_0_given_null() {
        // arrange

        // act
        int numberOfEvens = sut.countEvens(null);
        // assert
        Assert.assertEquals(0, numberOfEvens);
    }

    @Test
    public void countEvens_returns_2_given_2_evens_1_odd() {
        // arrange

        // act
        int[] testArr = new int[] {1,2,-4};
        int numberOfEvens = sut.countEvens(testArr);
        // assert
        Assert.assertEquals(2, numberOfEvens);
    }


}
