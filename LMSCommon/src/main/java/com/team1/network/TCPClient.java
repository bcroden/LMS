package com.team1.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.security.InvalidKeyException;

import com.team1.encryption.AESCipher;
import com.team1.encryption.RSACipher;

/**
 * Represents a TCP client.
 * This class is NOT thread safe.
 * This object will attempt to create a connection with a given server. Due to the current structure
 * of the server. An instance of a TCPClient object is only valid for one request/reply session. If
 * multiple request must be sent , one TCPClient object will need to be created for each request.
 * 
 * @author Alex Anderson
 */

public class TCPClient
{
    /**
     * Allows creation of a TCPClient by only specifying the host name and port number.
     * This will result in the client blocking while reading data from the server.
     * For a nonblocking client, use the other constructor
     * 
     * @author Alex Anderson
     * @param host String represents the name of the host
     * @param port Integer indicates the port number to which the server is listening
     * @throws IOException An error has occurred when initializing the connection to the server
     * @throws InvalidKeyException An error has occurred when initializing the encryption
     */
    public TCPClient(String host, int port) throws InvalidKeyException,
            IOException
    {
        this(host, port, 0);
    }

    /**
     * Allows creation of a TCPClient.
     * This will result a client which will block for a present number of
     * milliseconds when waiting for the server to send data.
     * 
     * @author Alex Anderson
     * @param host String representing the name of the host
     * @param port Integer indicating the port number to which the server is listening
     * @param timeout Time in milliseconds the client block when waiting for the server to send data
     * @throws IOException An error has occurred when initializing the connection to the server
     * @throws InvalidKeyException An error has occurred when initializing the encryption
     */
    public TCPClient(String host, int port, int timeout) throws IOException, InvalidKeyException
    {
        socket = new Socket(host, port);
        socket.setSoTimeout(timeout);

        // create streams for sending and
        // receiving information to/from server
        toServer = new DataOutputStream(socket.getOutputStream());
        fromServer = new DataInputStream(socket.getInputStream());

        // initiate encryption
        setupAESCipher();
    }

    /**
     * Encrypts a given string and sends it to the server.
     * This method should not be called more than once unless it failed the previous try.
     * 
     * @author Alex Anderson
     * @param request A String representing the request for the server
     * @throws IOException An error occurred while sending the data to the server
     * @throws InvalidKeyException An error occurred during the encryption process
     */
    public void sendRequest(String request) throws IOException, InvalidKeyException
    {
        byte[] encReq = cipher.encrypt(request);

        sendBytes(encReq);
    }

    /**
     * Attempts to read data sent by the server.
     * If the client is completely blocking, this method will block until data is sent by the server.
     * If the client has a timeout specified and no data has arrived by the specified waiting period,
     * this method will return null.
     * 
     * @return Either reply from server or null . See description for explanation .
     * @throws InvalidKeyException An error occurred during the decryption of the data from the server .
     * @throws IOException An error occurred while receiving the data from the server .
     */
    public String getReply() throws InvalidKeyException, IOException
    {
        try
        {
            byte[] encReply = readBytes();
            close();
            return cipher.decrypt(encReply);
        }
        catch(SocketTimeoutException e)
        {
            return null;
        }
    }

    /**
     * Combines the functionalities of sendRequest(String) and getReply().
     * This method will send a request to the server, and block until it receives a reply.
     * This method will always block even if a maximum wait time is specified in the constructor.
     * 
     * @author Alex Anderson
     * @param request A String representing the request for the server .
     * @return The reply by the server .
     * @throws InvalidKeyException An error occurred while en / decrypting the data .
     * @throws IOException A communications error has occurred .
     */
    public String requestAndWait(String request) throws InvalidKeyException, IOException
    {
        sendRequest(request);

        String reply;
        do
        {
            reply = getReply();
        }
        while(reply == null || reply == "");

        return reply;
    }

    /**
     * Closes the socket and all input/output streams used to communicate with the server.
     * This method call cannot be undone.
     * 
     * @throws IOException The socket, input stream, or output stream did not close properly.
     */
    public void close() throws IOException
    {
        fromServer.close();
        toServer.close();
        socket.close();
    }

    //send an array of raw bytes to the server
    private void sendBytes(byte[] bytes) throws IOException
    {
    	toServer.flush();
    	//System.out.println("In TCPClient sendBytes length: " + bytes.length);
        toServer.writeInt(bytes.length);
        toServer.write(bytes);
        toServer.flush();
    }

    //receive a sequence of raw bytes from the server
    private byte[] readBytes() throws IOException
    {
        int size = 0;
        while(size == 0)
            size = fromServer.readInt(); // Seems to return zero even when nothing has been sent
        
        byte[] msg = new byte[size];
        fromServer.readFully(msg);
        return msg;
    }

    private void setupAESCipher() throws IOException, InvalidKeyException
    {
    	//System.out.println("setting up cipher");
        //generate public key
        RSACipher publicCipher = new RSACipher();
        //System.out.println("building new cipher");
        byte[] publicKey = publicCipher.getPublicKey().getEncoded();

        //send server public key
        sendBytes(publicKey);

        //System.out.println("sent bytes");
        //decrypt private key sent by server
        byte[] privateKey = publicCipher.decrypt(readBytes());

        //return cipher based on server's key
        cipher = new AESCipher(privateKey);
        //System.out.println("built new Cipher");
    }

    private AESCipher cipher;
    private DataOutputStream toServer;
    private DataInputStream fromServer;
    private Socket socket;
}