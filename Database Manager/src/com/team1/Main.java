package com.team1;

import java.util.Scanner;
import java.util.ArrayList;

import com.team1.network.TCPServer;

import com.team1.db.Dbwrapper;

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
        
        //testing for database connection
        Dbwrapper db = Dbwrapper.getInstance();
        ArrayList list = new ArrayList();
        list = db.SearchAuthor("Me");
        int size = list.size();
        for(int i = 0; i < size; i++){
            System.out.println(list.get(i).toString());
        }
    }
}