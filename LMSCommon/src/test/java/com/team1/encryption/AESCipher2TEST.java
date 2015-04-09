package com.team1.encryption;

import static org.junit.Assert.assertTrue;

import java.security.InvalidKeyException;

import javax.crypto.SecretKey;

import org.junit.Test;

public class AESCipher2TEST {

	@Test
	public void test() throws InvalidKeyException {
        String message = "‡ŒË™ 	 ____ © System.out.println('«_') ڈڈڈڈڈڈڈڈڈڈ۞ڲ໇໰׉"
        		+ "Marry had a little lamb, little lamb, little lamb, marryhad"
        		+ "alittlelambwhosefleecewaswhiteasnowwwwwwwwwwwwwwwwwwwwwwww"
        		+ "zyxwvutsrqponlmkjihgfedcbanowiknowmyabc'snexttimewon'tyousing"
        		+ "with me. Marley's Ghost"
        		+"Marley was dead, to begin with. There is no doubt whatever about"
        		+"that. Theregister of his burial was signed by the clergyman, the cle"
        		+"rk, the undertaker, and the chief mourner. Scrooge signed it. And Scrooge’"
        		+"s name was good upon ’Change for anything he chose to put his hand to. Old"
        		+"Marley was as dead as a doornail.ind! I don’t mean to say that I know of my"
        		+"own knowledge, what there is particularly dead about a doornail. I might"
        		+"have been inclined, myself, to regard a coffin-nail as the deadest piece "
        		+"f ironmongery in the trade. But the wisdom of our ancestors is in the simile;"
        		+"and my unhallowed hands shall not disturb it, or the country’s done for. "
        		+"ou will, therefore, permit me to repeat, emphatically, that  Marley was "
        		+"as dead as a doornail.";
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
        
        assertTrue("AESCipher2 failed to maintain message integrity", message.equals(tmp));
	}
}