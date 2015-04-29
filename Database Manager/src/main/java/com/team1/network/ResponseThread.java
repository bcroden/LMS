package com.team1.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.security.InvalidKeyException;

import com.team1.encryption.AESCipher;
import com.team1.encryption.RSACipher;
import com.team1.formatting.QueryUtils;
import com.team1.formatting.queries.Query;
import com.team1.formatting.responses.Response;
	
/**
 * Represents a thread running on the server which will communicate with a
 * client. This class is designed to interface with com.team1.network.TCPClient
 * and handles all encryption needed to successfully communicate over the
 * network.
 * 
 * @author Alex Anderson
 */

public class ResponseThread extends Thread
{
    public ResponseThread(Socket socket, TCPServer boss) throws IOException, InterruptedException, InvalidKeyException
    {
        this.socket = socket;
        this.boss = boss;

        this.socket.setSoTimeout(1); // read time out is 1ms

        // setup streams to talk to client
        toClient = new DataOutputStream(socket.getOutputStream());
        fromClient = new DataInputStream(socket.getInputStream());

        setupAESCipher();
    }

    @Override
    public void run()
    {
        try
        {
        	 // get the request string from the client
        	String cliRequest = readClientRequest();
        	
        	//System.out.println("Request string from client: " + cliRequest);
        	//System.out.println("Crash at build request");
        	Query query = Query.buildRequest(cliRequest);
        	
        	//System.out.println("Got out of build");
        	//System.out.println("Result of build request: "+ query.toString());
        	
        	// TODO: Pass Query object to Authentication
        	Response response = QueryUtils.executeQuery(query);
        	//System.out.println("After execute query");
        	//System.out.println("From DBM Sending : " + response.toString());
        	// send reply string to the client
        	sendReplyToClient(response.toString()); // echo what was sent by the client
        	// clean up my mess
        	close();
        }
        catch(Exception e)
        {
            // Something bad has happened
            // notify the main server thread that we are terminating
            leaveBoss();
        }
    }

    // decrypts and returns the bytes sent by the client
    private String readClientRequest() throws IOException, InterruptedException, InvalidKeyException
    {
        byte[] encRequest = readBytes();
        return cipher.decrypt(encRequest);
    }

    // encrypts and sends a message to the client
    private void sendReplyToClient(String reply) throws InvalidKeyException, IOException
    {
    	
        sendBytes(cipher.encrypt(reply));
    }

    // reads the raw bytes sent by the client
    // if no bytes are sent, the thread will sleep until they arrive
    private byte[] readBytes() throws IOException, InterruptedException
    {
        /* Get Message Size */
        int msgSize = 0;
        while(msgSize == 0)
        {
            try
            {
                // Note: this seems to return 0 instead
                // of throwing a SocketTimeoutException
                msgSize = fromClient.read();
            }
            catch(SocketTimeoutException e)
            {
            }

            Thread.sleep(2);
        }

        /* Read Message */
        byte[] request = new byte[msgSize];
        while(true)
        {
            try
            {
                // keep attempting to read until it is successful
                fromClient.readFully(request);
              
                break;
            }
            catch(SocketTimeoutException e)
            {
            }

            Thread.sleep(2);
        }
        //System.out.println("Read Bytes Message msgSize: " + msgSize + " request length: " + request.length);
        return request;
    }

    // sends an array of raw bytes to the client
    private void sendBytes(byte[] bytes) throws IOException
    {
    	toClient.flush();
        toClient.writeInt(bytes.length);
        toClient.flush();
        //System.out.println("Sending message lengh: " + bytes.length);
        toClient.write(bytes);
        toClient.flush();
    }

    // initialize encryption ciphers according to an agreed protocol
    private void setupAESCipher() throws IOException, InterruptedException, InvalidKeyException
    {
        // create cipher using client's public key
        RSACipher publicCipher = new RSACipher(readBytes());

        // create private cipher
        cipher = new AESCipher();

        // send client encrypted private key
        byte[] privateKey = cipher.getKey().getEncoded();
        sendBytes(publicCipher.encrypt(privateKey));
    }

    // notify the main thread running the server that this thread is finished.
    private void leaveBoss()
    {
        boss.requestClientRemoval(this);
    }

    // notify the server thread that we are closing all streams and sockets
    private void close() throws IOException
    {
        fromClient.close();
        toClient.close();
        socket.close();
        leaveBoss();
    }

    private AESCipher cipher;
    private DataOutputStream toClient;
    private DataInputStream fromClient;
    private Socket socket;
    private TCPServer boss;
}