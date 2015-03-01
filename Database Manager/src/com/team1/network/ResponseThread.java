package com.team1.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Represents a thread which will read a message from a client.
 * @author Alex Anderson
 */

//NOTE: This class assumes that the newline character is the delimiter for network messages
public class ResponseThread extends Thread
{
	public ResponseThread(Socket socket, TCPServer boss)
	{
		this.socket = socket;
		this.boss = boss;
	}
	
	@Override
	public void run()
	{
		try
		{
			socket.setSoTimeout(1);	//read time out is 1ms
			
			//setup stream to read client's message
			BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			//read message
			String message = null;
			while(message == null || message.equalsIgnoreCase(""))
			{
				try
				{
					if(Thread.interrupted())
						break;
					
					message = bf.readLine();
				}
				catch(SocketTimeoutException e)
				{
					continue;
				}
			}
			
			if(Thread.interrupted())
			{
				bf.close();
				socket.close();
				return;
			}
			
			// TODO: Pass the message to the cryptography objects
			
			// TODO: Sleep until response is given from cryptography objects
			
			//setup stream to send reply to client
			
			//clean up my mess
			
			// TODO: Close stream used to send information to clients
			bf.close();
			socket.close();
			boss.requestClientRemoval(this);
		}
		catch (IOException e)
		{
			//Something really bad has happened...
			//Probable cause is that the client has closed its connection.
			// TODO: Are we going to have a log file for dropped connections etc?
			e.printStackTrace();
		}
	}
	
	private Socket socket;
	private TCPServer boss;
}