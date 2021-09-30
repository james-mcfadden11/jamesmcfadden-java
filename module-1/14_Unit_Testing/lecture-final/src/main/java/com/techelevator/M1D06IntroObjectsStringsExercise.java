package com.techelevator;

public class M1D06IntroObjectsStringsExercise {

    /*
	 Given a string, return true if "bad" appears starting at index 0 or 1 in the string, such as with
	 "badxxx" or "xbadxx" but not "xxbadxx". The string may be any length, including 0. Note: use .equals()
	 to compare 2 strings.
	 hasBad("badxx") → true
	 hasBad("xbadxx") → true
	 hasBad("xxbadxx") → false
	 */
    public boolean hasBad(String str) {

        if(str == null) {
            return false;
        }

        int indexOfBad = str.indexOf("bad");
        if(indexOfBad == 0 || indexOfBad == 1) {
            return true;
        }

        return false;
    }
}
