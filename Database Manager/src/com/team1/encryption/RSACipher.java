package com.team1.encryption;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * A cipher utilizing the RSA algorithm.
 * This cipher will encrypt messages with a public 1024-bit key,
 * and will decrypt messages with a private 2048-bit key.
 * 
 * NOTE: Using this cipher to encrypt any message longer then 112 bytes
 * will result in insecure ciphertext. I will fix this later, but since
 * we will only use this to encrypt a 16 byte AES key, I do not currently
 * consider it a priority.
 * 
 * @author Brandon Roden
 *
 */
public class RSACipher {
	public static final int KEY_SIZE = 1024;
	public static final int BLOCK_SIZE = 112;
	public static final int MAX_BLOCK_SIZE = 117;
	public static final int ENCRYPTED_BLOCK_LENGTH = 128;
	
	public static final String ALGORITHM = "RSA";
	public static final String DEFAULT_CHARSET = "UTF-8";
	public static final String TRANSFORMATION = "RSA/ECB/PKCS1Padding";
	
	public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
		KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITHM);
		kpg.initialize(KEY_SIZE);
		return kpg.generateKeyPair();
	}
	
	public static PublicKey generatePublicKey(byte[] keyBytes) {
		try {
			return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(keyBytes));
		}
		catch (InvalidKeySpecException e) {
			//Thrown if keyBytes are invalid
			e.printStackTrace();
		}
		catch (NoSuchAlgorithmException e) {
			//Thrown if RSA is not a supported encryption algorithm
			//Will never occur since it is guaranteed to be implemented by all JREs
			e.printStackTrace();
		}
		return null;
	}
	
	public static PrivateKey generatePrivateKey(byte[] keyBytes) {
		try {
			return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(keyBytes));
		}
		catch (InvalidKeySpecException e) {
			//Thrown if keyBytes are invalid
			e.printStackTrace();
		}
		catch (NoSuchAlgorithmException e) {
			//Thrown if RSA is not a supported encryption algorithm
			//Will never occur since it is guaranteed to be implemented by all JREs
			e.printStackTrace();
		}
		return null;
	}
	
	private Cipher cipher;
	private PublicKey publicKey;
	private PrivateKey privateKey;
	
	/**
	 * Create a RSACipher object. This will generate new keys.
	 */
	public RSACipher() {
		try {
			this.cipher = Cipher.getInstance(TRANSFORMATION);
			KeyPair kp = generateKeyPair();
			this.publicKey = kp.getPublic();
			this.privateKey = kp.getPrivate();
		}
		catch (NoSuchAlgorithmException e) {
			//Thrown if RSA is not a supported encryption algorithm
			//Will never occur since it is guaranteed to be implemented by all JREs
			e.printStackTrace();
		}
		catch (NoSuchPaddingException e) {
			//Thrown if PKCS1 is not a supported padding system
			//Will never occur since it is guaranteed to be implemented by all JREs
			e.printStackTrace();
		}
	}
	
	/**
	 * Create a RSACipher object. This will use the provided public key to encrypt messages.
	 * This cipher will be unable to decrypt messages, since it has no private key.
	 */
	public RSACipher(PublicKey publicKey) {
		try {
			this.cipher = Cipher.getInstance(TRANSFORMATION);
			this.publicKey = publicKey;
			this.privateKey = null;
		}
		catch (NoSuchAlgorithmException e) {
			//Thrown if RSA is not a supported encryption algorithm
			//Will never occur since it is guaranteed to be implemented by all JREs
			e.printStackTrace();
		}
		catch (NoSuchPaddingException e) {
			//Thrown if PKCS1 is not a supported padding system
			//Will never occur since it is guaranteed to be implemented by all JREs
			e.printStackTrace();
		}
	}
	
	/**
	 * Create a RSACipher object. This will use the provided public and private keys for
	 * encryption and decryption.
	 */
	public RSACipher(PublicKey publicKey, PrivateKey privateKey) {
		try {
			this.cipher = Cipher.getInstance(TRANSFORMATION);
			this.publicKey = publicKey;
			this.privateKey = privateKey;
		}
		catch (NoSuchAlgorithmException e) {
			//Thrown if RSA is not a supported encryption algorithm
			//Will never occur since it is guaranteed to be implemented by all JREs
			e.printStackTrace();
		}
		catch (NoSuchPaddingException e) {
			//Thrown if PKCS1 is not a supported padding system
			//Will never occur since it is guaranteed to be implemented by all JREs
			e.printStackTrace();
		}
	}
	
	/**
	 * This method will encrypt a byte[] using the RSA encryption algorithm.
	 * 
	 * @param message - The byte[] to be encrypted
	 * @return A byte[] containing the encrypted bytes
	 * @throws InvalidKeyException If the current key is invalid
	 */
	//TODO: Don't use separate block encryption. It's bad for your soul.
	public byte[] encrypt(byte[] message) throws InvalidKeyException {
		//Calculate the number of passes required to encrypt entire message
		int passes = (int) Math.ceil(((double)message.length / BLOCK_SIZE));
		
		//Initialize the cipher
		this.cipher.init(Cipher.ENCRYPT_MODE, this.publicKey);
		
		//Encrypt every block separately
		byte[] encryptedBytes = null;
		for(int i = 0; i < passes; i++) {
			//Get current message bytes to encrypt
			byte[] temp = Util.getSubArray(message, i*BLOCK_SIZE, BLOCK_SIZE);
			try {
				//Add newly encrypted bytes to total
				encryptedBytes = Util.concatenateArrays(encryptedBytes, this.cipher.doFinal(temp));
			}
			catch (IllegalBlockSizeException e) {
				//Occurs if block size is not multiple of 128
				e.printStackTrace();
			}
			catch (BadPaddingException e) {
				//Occurs if message padding is not correct as per PKCS1 standards
				e.printStackTrace();
			}
		}
		
		return encryptedBytes;
	}
	
	/**
	 * 
	 * 
	 * @param encryptedBytes - The byte[] to be decrypted
	 * @return A byte[] containing the decrypted bytes
	 * @throws InvalidKeyException If the current key is invalid
	 */
	public byte[] decrypt(byte[] encryptedBytes) throws InvalidKeyException {
		//Calculate the number of passes required to decrypt entire message
		int passes = (int) Math.ceil(((double)encryptedBytes.length / ENCRYPTED_BLOCK_LENGTH));
		
		//Initialize the cipher
		this.cipher.init(Cipher.DECRYPT_MODE, this.privateKey);
		
		//Decrypt every block separately
		byte[] message = null;
		for(int i = 0; i < passes; i++) {
			//Get current encrypted bytes to decrypt
			byte[] temp = Util.getSubArray(encryptedBytes, i*ENCRYPTED_BLOCK_LENGTH, ENCRYPTED_BLOCK_LENGTH);
			try {
				//Add newly decrypted bytes to total
				message = Util.concatenateArrays(message, this.cipher.doFinal(temp));
			}
			catch (IllegalBlockSizeException e) {
				//Occurs if block size is not multiple of 128
				e.printStackTrace();
			}
			catch (BadPaddingException e) {
				//Occurs if message padding is not correct as per PKCS1 standards
				e.printStackTrace();
			}
		}
		
		return message;
	}
	
	//TODO Be able to load keys from a file
	public void loadKeysFromFile() {
	}
		
	//TODO Be able to save keys to a file
	public void saveKeysToFile() {
	}
	
	//Basic main for testing purposes
	public static void main(String args[]) throws Exception {
		//Create new cipher
		RSACipher cipher = new RSACipher();
		
		//Generate test AES key
		byte[] bytes = AESCipher.generateKey().getEncoded();
		
		//Test Reconstruction of public and private keys
		byte[] keyBytes = cipher.publicKey.getEncoded();
		PublicKey pub = generatePublicKey(keyBytes);
		cipher.publicKey = pub;
		
		keyBytes = cipher.privateKey.getEncoded();
		PrivateKey priv = generatePrivateKey(keyBytes);
		cipher.privateKey = priv;
		
		System.out.println("Original Message:  " + new String(bytes, "UTF-8"));
		
		//Encrypt message
		long timer = System.nanoTime();
		byte[] enbytes = cipher.encrypt(bytes);
		long enTime = System.nanoTime() - timer;
		
		System.out.println("Encrypted Message: " + new String(enbytes, "UTF-8"));
		
		//Decrypt message
		timer = System.nanoTime();
		bytes = cipher.decrypt(enbytes);
		long deTime = System.nanoTime() - timer;
		
		System.out.println("Decrypted Message: " + new String(bytes, "UTF-8"));
		
		System.out.println("Encryption Took: " + enTime + " nanoseconds");
		System.out.println("Decryption Took: " + deTime + " nanoseconds");
	}
}