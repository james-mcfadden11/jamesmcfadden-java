package com.techelevator;

import org.junit.*;

public class M1D04LoopsArraysExerciseTest {
    private M1D04LoopsArraysExercise sut;

    @Before
    public void setup() {
        sut = new M1D04LoopsArraysExercise();
    }


    /*

     Test Cases

	 Input       Output
	 null          0
	 []            0
	 [1, -1]       0
	 [2, -4]       2
	 [1, 2, -4]    2

     */

    @Test
    public void countEvens_returns_0_given_null() {
        // Arrange

        // Act
        int numberOfEvens = sut.countEvens(null);

        // Assert
        Assert.assertEquals(0, numberOfEvens);
    }

    @Test
    public void countEvens_returns_2_given_2_evens_1_odd() {
        // Arrange

        // Act
        int numberOfEvens = sut.countEvens(new int[] { 1, 2, -4 });

        // Assert
        Assert.assertEquals(2, numberOfEvens);
    }
}
