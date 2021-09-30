package com.techelevator;

public class M1D04LoopsArraysExercise {
    /*
	 11. Return the number of even ints in the given array. Note: the % "mod" operator computes the
	 remainder, e.g. 5 % 2 is 1.

	 */
    public int countEvens(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int evenIntCount = 0;

        for (int num : nums) {
            if (num % 2 == 0) {
                evenIntCount++;
            }
        }

        return evenIntCount;
    }
}
