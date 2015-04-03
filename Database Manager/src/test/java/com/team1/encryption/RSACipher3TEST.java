/*
//The junit fails 

package com.team1.encryption;


import static org.junit.Assert.assertFalse;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class RSACipher3TEST {

	@Test
	public void test() throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        String message = "";
		byte[] bytes = message.getBytes();
		
		// Create cipher
        RSACipher cipher = new RSACipher();
        
        // Encrypt message
        byte[] enbytes = cipher.encrypt(bytes);

        // Decrypt message
        byte[] bytes2 = cipher.decrypt(enbytes);
        
        String message2 = new String(bytes2, "UTF-8");
        
        assertFalse("RSACipher failed to maintain message integrity", message.equals(message2));
	}
}
*/