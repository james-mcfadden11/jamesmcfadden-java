package com.techelevator;

import com.techelevator.security.AESEncryption;
import com.techelevator.view.Menu;

public class EncryptionApp {

	public static void main(String[] args) {
		AESEncryption aesEncryption = new AESEncryption();
		FileEncryption fileEncryption = new FileEncryption(aesEncryption);
		
		Menu menu = new Menu(System.in, System.out);
		EncryptionCLI cli = new EncryptionCLI(menu, fileEncryption);
		
		cli.run();
	}

}
