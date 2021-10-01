package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DateFashionTest {
    private DateFashion sut;

    /*
    test cases:
        one person 8 or above & one person 3-7 --> 2
        one person 2 or below & one person 8 or above --> 0
        one person 2 or below & one person 3-7 --> 0
        both between 3-7 --> 1

        2 or lower = low
        3-7 = medium
        8 or above = high
    */

    @Before
    public void setup() {
        sut = new DateFashion();
    }

    // one person 8 or above & one person 3-7 --> 2
    @Test
    public void getATable_returns_2_given_one_high_one_medium() {
        // arrange
        // act
        int gotATable = sut.getATable(8, 5);
        // assert
        Assert.assertEquals(2, gotATable);
    }

    // one person 2 or below & one person 8 or above --> 0
    @Test
    public void getATable_returns_0_given_one_high_one_low() {
        // arrange
        // act
        int gotATable = sut.getATable(8, 1);
        // assert
        Assert.assertEquals(0, gotATable);
    }

    // one person 2 or below & one person 3-7 --> 0
    @Test
    public void getATable_returns_0_given_one_low_one_medium() {
        // arrange
        // act
        int gotATable = sut.getATable(2, 5);
        // assert
        Assert.assertEquals(0, gotATable);
    }

    // both between 3-7 --> 1
    @Test
    public void getATable_returns_1_given_both_medium() {
        // arrange
        // act
        int gotATable = sut.getATable(4, 7);
        // assert
        Assert.assertEquals(1, gotATable);
    }

}
