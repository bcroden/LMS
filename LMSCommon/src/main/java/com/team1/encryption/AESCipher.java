package com.team1.encryption;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * A cipher utilizing the AES algorithm. This cipher will encrypt and decrypt
 * messages using a shared 128-bit key.
 * 
 * @author Brandon Roden
 *
 */
public class AESCipher {
    public static final int IV_SIZE = 16;
    public static final int KEY_SIZE = 128;

    public static final String ALGORITHM = "AES";
    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final String TRANSFORMATION = "AES/CBC/PKCS5PADDING";

    public static SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        synchronized (keyGen) {
            keyGen.init(KEY_SIZE);
        }
        return keyGen.generateKey();
    }

    public static SecretKey generateKey(byte[] key) {
        return new SecretKeySpec(key, ALGORITHM);
    }

    private Cipher cipher;
    private SecretKey key;

    /**
     * Create an AESCipher object. This will generate a new AES key.
     */
    public AESCipher() {
        try {
            this.cipher = Cipher.getInstance(TRANSFORMATION);
            this.key = generateKey();
        }
        catch (NoSuchAlgorithmException e) {
            // Thrown if AES is not a supported encryption algorithm
            // Will never occur since it is guaranteed to be implemented by all
            // JREs
        }
        catch (NoSuchPaddingException e) {
            // Thrown if PKCS5 is not a supported padding system
            // Will never occur since it is guaranteed to be implemented by all
            // JREs
        }
    }

    /**
     * Create an AESCipher object. This will regenerate an AES key based on the
     * provided byte[].
     * 
     * @param key
     *            - A byte[] representing a SecretKey.
     */
    public AESCipher(byte[] key) {
        try {
            this.cipher = Cipher.getInstance(TRANSFORMATION);
        }
        catch (NoSuchAlgorithmException e) {
            // Thrown if AES is not a supported encryption algorithm
            // Will never occur since it is guaranteed to be implemented by all
            // JREs
        }
        catch (NoSuchPaddingException e) {
            // Thrown if PKCS5 is not a supported padding system
            // Will never occur since it is guaranteed to be implemented by all
            // JREs
        }

        this.key = new SecretKeySpec(key, ALGORITHM);
    }

    /**
     * Create an AESCipher object. This will use the provided SecretKey for the
     * key.
     * 
     * @param key
     *            - A valid AES SecretKey
     */
    public AESCipher(SecretKey key) throws InvalidKeyException {
        try {
            this.cipher = Cipher.getInstance(TRANSFORMATION);
        }
        catch (NoSuchAlgorithmException e) {
            // Thrown if AES is not a supported encryption algorithm
            // Will never occur since it is guaranteed to be implemented by all
            // JREs
        }
        catch (NoSuchPaddingException e) {
            // Thrown if PKCS5 is not a supported padding system
            // Will never occur since it is guaranteed to be implemented by all
            // JREs
        }

        if (!key.getAlgorithm().equals(ALGORITHM))
            throw new InvalidKeyException();
        else
            this.key = key;
    }

    /**
     * This method will run the AES encryption algorithm on the provided
     * message.
     * 
     * @param message
     *            - The String to be encrypted
     * @return A byte[] containing the encrypted bytes
     * @throws InvalidKeyException
     *             If the current key is invalid
     */
    public byte[] encrypt(String message) throws InvalidKeyException {
        // Convert message to byte[]
        byte[] messageBytes;
        try {
            messageBytes = message.getBytes(DEFAULT_CHARSET);
        }
        catch (UnsupportedEncodingException e) {
            // Use system default charset
            messageBytes = message.getBytes();
        }

        // Generate initialization vector
        byte[] iv = new byte[IV_SIZE];
        new SecureRandom().nextBytes(iv);

        // Initialize cipher
        try {
            synchronized (this.cipher) {
                this.cipher.init(Cipher.ENCRYPT_MODE, this.key, new IvParameterSpec(iv));
            }
        }
        catch (InvalidAlgorithmParameterException e) {
            // Occurs if iv is not specified or is invalid
            e.printStackTrace();
        }

        // Encrypt message
        byte[] encryptedBytes = { 0 };
        try {
            synchronized (this.cipher) {
                encryptedBytes = this.cipher.doFinal(messageBytes);
            }
        }
        catch (IllegalBlockSizeException e) {
            // Occurs if block size is not multiple of 16
            e.printStackTrace();
        }
        catch (BadPaddingException e) {
            // Occurs if message padding is not correct as per PKCS5 standards
            e.printStackTrace();
        }

        // Append encryptedBytes to iv for transmission
        byte[] encryptedMessage = EncryptionUtils.concatenateArrays(iv, encryptedBytes);

        // Transmit
        //System.out.println("In encryption, Encrypted bytes length = " + encryptedMessage.length);
        
        return encryptedMessage;
    }

    /**
     * This method will run the AES decryption algorithm on the provided byte[].
     * 
     * @param encryptedMessage
     *            - The byte[] containing the encrypted bytes
     * @return A String containing the decrypted message.
     * @throws InvalidKeyException
     *             If the current key is invalid
     */
    public String decrypt(byte[] encryptedMessage) throws InvalidKeyException {
    	//System.out.println("In decrypt messageLength = " + encryptedMessage.length);
        // Separate initialization vector from encrypted message
        byte[] iv = EncryptionUtils.getSubArray(encryptedMessage, 0, IV_SIZE);
        byte[] encryptedBytes = EncryptionUtils.getSubArray(encryptedMessage, IV_SIZE, encryptedMessage.length - IV_SIZE);

        
        
        // Initialize cipher
        try {
            synchronized (this.cipher) {
                this.cipher.init(Cipher.DECRYPT_MODE, this.key, new IvParameterSpec(iv));
            }
        }
        catch (InvalidAlgorithmParameterException e) {
            // Occurs if iv is not specified or is invalid
            e.printStackTrace();
        }

        // Decrypt message
        byte[] message = { 0 };
        try {
            synchronized (this.cipher) {
                message = this.cipher.doFinal(encryptedBytes);
            }
        }
        catch (IllegalBlockSizeException e) {
            // Occurs if block size is not multiple of 16
            e.printStackTrace();
        }
        catch (BadPaddingException e) {
            // Occurs if message padding is not correct as per PKCS5 standards
            e.printStackTrace();
        }

        // Convert message to String
        try {
            return new String(message, DEFAULT_CHARSET);
        }
        catch (UnsupportedEncodingException e) {
            // Use system default charset
            return new String(message);
        }
    }

    public SecretKey getKey() {
        return this.key;
    }

    public void setKey(SecretKey key) throws InvalidKeyException {
        if (!key.getAlgorithm().equals(ALGORITHM))
            throw new InvalidKeyException();
        else
            this.key = key;
    }
}