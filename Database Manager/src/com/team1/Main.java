package com.team1;

import java.util.Scanner;

import com.team1.network.TCPServer;

public class Main
{
	public static void main(String args[]) throws Exception
	{
		System.out.println("Hello World!");
		
		//setup TCP server on port 3612
		Thread thread = new Thread(new TCPServer(3612));
		thread.start();
		
		System.out.println("Enter anything to interrupt the server");
		
		//wait for the user to enter anything
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		scanner.close();
		
		//stop the server
		thread.interrupt();
		thread.join();
	}
}