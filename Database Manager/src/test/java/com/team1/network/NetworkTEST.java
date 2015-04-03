package com.team1.network;

import java.io.IOException;
import java.security.InvalidKeyException;

import org.junit.Test;

/**
 * Simple JUnit test case to ensure that the client and server operate correctly
 * together
 * 
 * @author Alex
 */

public class NetworkTEST
{
    @Test
    public void encryptionTestEchoServer() throws IOException, InterruptedException, InvalidKeyException
    {
        final int PORT = 3612;
        final String hostname = "localhost";

//        // setup TCP server on the specified port
//        Thread thread = new Thread(new TCPServer(PORT));
//        thread.start();
//        
//        MockTCPClient client = new MockTCPClient(hostname, PORT);
//
//        Query query = new CheckOutBookQuery(false, "", "054792822X", "", "", "", "", "", "");
//        
//        System.out.println("About to send " + query + " to server.");
//        
//        String reply = client.requestAndWait(query.toString());
//        
//        System.out.println("Just got " + reply + " from server");
//        
//        Query response = Query.buildRequest(reply);
//
//        assertTrue("Reply from echo server does not match request", response.wasSuccessful);
//
//        // stop the server
//        thread.interrupt();
//        thread.join();
    }
}