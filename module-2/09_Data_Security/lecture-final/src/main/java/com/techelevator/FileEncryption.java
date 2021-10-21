package com.techelevator;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;

import com.techelevator.security.AESEncryption;

public class FileEncryption {
	private AESEncryption encryption;
	
	public FileEncryption(AESEncryption encryption) {
		this.encryption = encryption;
	}
	
	public void createKeyFile(String path) throws NoSuchAlgorithmException, IOException {
		byte[] keyBytes = encryption.createAESKey().getEncoded();
		writeFile(keyBytes, path);
	}
	
	public void encryptFile(String pathToEncrypt, String keyFilePath) throws Exception {
		String plainText = getPlainTextFromFile(pathToEncrypt);
		byte[] keyBytes = getAllBytesFromFile(keyFilePath);
		byte[] iv = encryption.createInitializationVector();
		byte[] encryptedBytes = encryption.do_AESEncryption(plainText, encryption.getKey(keyBytes), iv);
		writeFile(encryptedBytes, pathToEncrypt);
		writeFile(iv, "iv");
	}
	 
	public void decryptFile(String pathToDecrypt, String keyFilePath) throws Exception {
		byte[] encryptedBytes = getAllBytesFromFile(pathToDecrypt);
		byte[] keyBytes = getAllBytesFromFile(keyFilePath);
		byte[] iv = getAllBytesFromFile("iv");
		String plainText = encryption.do_AESDecryption(encryptedBytes, encryption.getKey(keyBytes), iv);
		writeFile(plainText.getBytes(), pathToDecrypt);
	}
	
	
	private String getPlainTextFromFile(String path) throws IOException {		
		byte[] bytes = getAllBytesFromFile(path);
		
		return new String(bytes, StandardCharsets.UTF_8);
	}
	
	private byte[] getAllBytesFromFile(String path) throws IOException {
		File file = new File(path);
		if(!file.exists()) {
			System.out.println("File does not exist");
		}
		
		return Files.readAllBytes(file.toPath());
	}
	
	private void writeFile(byte[] bytes, String path) throws IOException {
		File file = new File(path);
		
		Files.write(file.toPath(), bytes);
	}
}
