package com.team1.network;

/**
 * Simple JUnit test case to ensure that the client and server operate correctly
 * together
 * 
 * @author Alex
 */

public class NetworkTEST
{/*
    @Test
    public void encryptionTestEchoServer() throws IOException, InterruptedException, InvalidKeyException
    {
        final int PORT = 3612;
        final String hostname = "localhost";

        String str = "HelloHelloHelloHelloHelloHelloHelloHelloHello"
        		+ "HelloHelloHelloHelloHelloHelloHelloHelloHello"
        		+ "HelloHelloHelloHelloHelloHelloHelloHelloHello"
        		+ "HelloHelloHelloHelloHelloHelloHelloHelloHello"
        		+ "HelloHelloHelloHelloHelloHelloHelloHelloHello"
        		+ "HelloHelloHelloHelloHelloHelloHelloHelloHello"
        		+ "HelloHelloHelloHelloHelloHelloHelloHelloHello"
        		+ "HelloHelloHelloHelloHelloHelloHelloHelloHello"
        		+ "HelloHelloHelloHelloHelloHelloHelloHelloHello"
        		+ "HelloHelloHelloHelloHelloHelloHelloHelloHello"
        		+ "HelloHelloHelloHelloHelloHelloHelloHelloHello"
        		+ "HelloHelloHelloHelloHelloHelloHelloHelloHello"
        		+ "HelloHelloHelloHelloHelloHelloHelloHelloHello"
        		+ "HelloHelloHelloHelloHelloHelloHelloHelloHello"
        		+ "HelloHelloHelloHelloHelloHelloHelloHelloHello"
        		+ "HelloHelloHelloHelloHelloHelloHelloHelloHello"
        		+ "HelloHelloHelloHelloHelloHelloHelloHelloHello"
        		+ "HelloHelloHelloHelloHelloHelloHelloHelloHello"
        		+ "HelloHelloHelloHelloHelloHelloHelloHelloHello"
        		+ "HelloHelloHelloHelloHelloHelloHelloHelloHello"
        		+ "HelloHelloHelloHelloHelloHelloHelloHelloHello"
        		+ "HelloHelloHelloHelloHelloHelloHelloHelloHello";
        
        // setup TCP server on the specified port
        Thread thread = new Thread(new TCPServer(PORT));
        thread.start();
        
        MockTCPClient client = new MockTCPClient(hostname, PORT);
        
        String str2 = client.requestAndWait(str);
        
        // stop the server
        thread.interrupt();
        thread.join();
        
        //System.out.println("Str = " + str);
        //System.out.println("Str2 = " + str2);
        
        Response r = new Response(false, "");
        
        assertTrue("Failed", str2.equals(str));
    }*/
}