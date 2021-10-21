package com.techelevator;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import com.techelevator.view.Menu;

public class EncryptionCLI {

	private static final String MENU_OPTION_GENERATE_KEY = "Generate New Symmetric Key";
	private static final String MENU_OPTION_ENCRYPT = "Encrypt a file";
	private static final String MENU_OPTION_DECRYPT = "Decrypt a file";

	private static final String[] MENU_OPTIONS = 
		{ 
			MENU_OPTION_GENERATE_KEY, 
			MENU_OPTION_ENCRYPT,
			MENU_OPTION_DECRYPT 
		};
	
	private Menu menu;
	private FileEncryption encryption;
	
	public EncryptionCLI(Menu menu, FileEncryption encryption) {
		this.menu = menu;
		this.encryption = encryption;
	}

	public void run() {		
		String menuChoice = (String) menu.getChoiceFromOptions(MENU_OPTIONS);

		switch(menuChoice) {
		case MENU_OPTION_GENERATE_KEY:
			
			try {
				final String path = "key_file.key";
				
				encryption.createKeyFile(path);
				
				System.out.println("Key created at " + path);
				
			} catch (NoSuchAlgorithmException e) {
				System.out.println("Invalid encryption algorithm chosen.");
			} catch (IOException e) {
				System.out.println("Invalid file path chosen.");
			}
			
			break;
			
		case MENU_OPTION_ENCRYPT:

			try {
				System.out.println("Path to file to encrypt >>> ");
				String pathToEncrypt = menu.getNextLineOfInput();
				
				System.out.println("Path to key file >>> ");
				String keyFilePath = menu.getNextLineOfInput();
				
				encryption.encryptFile(pathToEncrypt, keyFilePath);
				
				System.out.println("Success");
							
			} catch (Exception e) {
				System.out.println("Something went wrong.");
				e.printStackTrace();
			}
			
			break;
			
		case MENU_OPTION_DECRYPT:
			
			try {
				System.out.println("Path to file to decrypt >>> ");
				String pathToDecrypt = menu.getNextLineOfInput();
				
				System.out.println("Path to key file >>> ");
				String keyFilePath = menu.getNextLineOfInput();
				
				encryption.decryptFile(pathToDecrypt, keyFilePath);
				
				System.out.println("Success");
							
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			break;
		}
	}

	
}
