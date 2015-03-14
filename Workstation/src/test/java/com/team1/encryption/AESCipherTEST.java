package com.team1.encryption;

import static org.junit.Assert.assertTrue;

import java.security.InvalidKeyException;

import javax.crypto.SecretKey;

import org.junit.Test;

public class AESCipherTEST {

	@Test
	public void test() throws InvalidKeyException {
        String message = "GabeN!";

        // Create cipher
        AESCipher cipher = new AESCipher();

        // Encrypt message
        byte[] encryptedBytes = cipher.encrypt(message);
        
        // Convert original key to byte[]
        byte[] originalBytes = cipher.getKey().getEncoded();
        
        // Reconstruct key from byte[]
        SecretKey reconstructedKey = AESCipher.generateKey(originalBytes);
        
        // Decrypt use reconstructed key for decryption
        cipher.setKey(reconstructedKey);

        // Decrypt message
        String tmp = cipher.decrypt(encryptedBytes);
        
        assertTrue("AESCipher failed to maintain message integrity", message.equals(tmp));
	}
}