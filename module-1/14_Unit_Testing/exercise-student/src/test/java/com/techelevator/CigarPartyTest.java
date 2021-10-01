package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CigarPartyTest {
    private CigarParty sut;

    /*
    test cases:
        weekend false
            30
            40
            60
            70
        weekend true
            70
    */

    @Before
    public void setup() {
        sut = new CigarParty();
    }

    // weekend false, 30 cigars
    @Test
    public void haveParty_returns_false_weekend_false_30_cigars() {
        // arrange
        // act
        boolean isPartySuccessful = sut.haveParty(30, false);
        // assert
        Assert.assertFalse(isPartySuccessful);
    }

    // weekend false, 40 cigars
    @Test
    public void haveParty_returns_true_weekend_false_40_cigars() {
        // arrange
        // act
        boolean isPartySuccessful = sut.haveParty(40, false);
        // assert
        Assert.assertTrue(isPartySuccessful);
    }

    // weekend false, 60 cigars
    @Test
    public void haveParty_returns_true_weekend_false_60_cigars() {
        // arrange
        // act
        boolean isPartySuccessful = sut.haveParty(60, false);
        // assert
        Assert.assertTrue(isPartySuccessful);
    }

    // weekend false, 70 cigars
    @Test
    public void haveParty_returns_false_weekend_false_70_cigars() {
        // arrange
        // act
        boolean isPartySuccessful = sut.haveParty(70, false);
        // assert
        Assert.assertFalse(isPartySuccessful);
    }

    // weekend true, 70 cigars
    @Test
    public void haveParty_returns_true_weekend_false_70_cigars() {
        // arrange
        // act
        boolean isPartySuccessful = sut.haveParty(70, true);
        // assert
        Assert.assertTrue(isPartySuccessful);
    }

}
