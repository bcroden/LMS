package com.team1.network;

import java.io.IOException;
import java.security.InvalidKeyException;

import org.junit.Test;

import com.team1.network.TCPClient;
import com.team1.network.TCPServer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Simple JUnit test case to ensure that the client and server operate correctly together
 * 
 * @author Alex
 */

public class NetworkTEST
{
	@Test
	public void encryptionTestEchoServer() throws IOException, InterruptedException, InvalidKeyException
	{
		final int PORT = 3612;
		
		//setup TCP server on the specified port
		Thread thread = new Thread(new TCPServer(PORT));
		thread.start();
		
		TCPClient client = new TCPClient("localhost", PORT);
		
		String request = "What is in my pocket?";
		
		String reply = client.requestAndWait(request);
		
		assertThat("Reply from echo server does not match request", request, is(reply));
		
		//stop the server
		thread.interrupt();
		thread.join();
	}
}