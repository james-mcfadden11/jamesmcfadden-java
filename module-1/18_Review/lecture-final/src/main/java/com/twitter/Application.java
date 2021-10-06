package com.twitter;

import com.twitter.ui.VoidView;

public class Application {

	public static void main(String[] args) {
		Twitter t = new Twitter(new VoidView());
		t.start();
	}

}
