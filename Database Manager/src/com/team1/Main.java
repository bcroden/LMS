package com.team1;

import java.util.Scanner;
import java.util.ArrayList;

import com.team1.network.TCPServer;

import com.team1.db.Dbwrapper;
import com.team1.books.*;

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
        
        
        //This section is TO DELETE for later
        //for now: testing for database connection
        Dbwrapper db = Dbwrapper.getInstance();
        
        //Example of getting query results and printing them
        //Single result
        System.out.println("\nSearch for book with ISBN: 1234\n");
        Book book = db.SearchISBN("1234");
        System.out.println(book.toString());
        
        //Multiple results
        System.out.println("\nSearch for all books with Author: Me\n");
        ArrayList list = new ArrayList();
        list = db.SearchAuthor("Me");
        int size = list.size();
        for(int i = 0; i < size; i++){
            System.out.println(list.get(i).toString());
        }
        System.out.println(book.toString());
        
        
        
        //ISBNs are unique ids so you can't input these more than once
        Adding a new book to the repo
        Book bookthing = BookFinder.getBookFromGoogle("054792822X");
        db.addBook(bookthing);
        
        Book book2 = BookFinder.getBookFromGoogle("0439136369");
        db.addBook(book2);
        
    }
}