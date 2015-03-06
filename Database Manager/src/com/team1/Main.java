package com.team1;

import java.util.Scanner;

import com.team1.network.TCPServer;

public class Main
{
    public static void main(String args[]) throws Exception
    {
        final int PORT = 3612;

        // setup TCP server on the specified port
        Thread thread = new Thread(new TCPServer(PORT));
        thread.start();

        System.out.println("Server has started.\nPress enter to stop the server");

        // wait for the user to press enter
        new Scanner(System.in).nextLine();

        // stop the server
        thread.interrupt();
        thread.join();
    }
}