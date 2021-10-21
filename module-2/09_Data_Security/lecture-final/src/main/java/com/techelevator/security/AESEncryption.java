package com.techelevator.security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESEncryption {

	public static final String AES = "AES";
	private static final String AES_CIPHER_ALGORITHM = "AES/CBC/PKCS5PADDING";

	public SecretKey createAESKey() throws NoSuchAlgorithmException {
		SecureRandom securerandom = new SecureRandom();
		KeyGenerator keygenerator = KeyGenerator.getInstance(AES);
		keygenerator.init(256, securerandom);
		SecretKey key = keygenerator.generateKey();
		
		return key;
	}
	
	public SecretKey getKey(byte[] keyBytes) {
		return new SecretKeySpec(keyBytes, AES);
	}

	public byte[] createInitializationVector() {
		byte[] initializationVector = new byte[16];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(initializationVector);
		return initializationVector;
	}

	public byte[] do_AESEncryption(String plainText, SecretKey secretKey, byte[] initVector)
			throws Exception {
		Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
		
		IvParameterSpec ivParameterSpec = new IvParameterSpec(initVector);

		cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);

		return cipher.doFinal(plainText.getBytes());
	}

	public String do_AESDecryption(byte[] cipherText, SecretKey secretKey, byte[] initVector)
			throws Exception {
		Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);

		IvParameterSpec ivParameterSpec = new IvParameterSpec(initVector);

		cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);

		byte[] result = cipher.doFinal(cipherText);

		return new String(result);
	}
}