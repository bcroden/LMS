package com.team1;

import java.util.Scanner;
import java.util.ArrayList;

import com.team1.network.MockTCPClient;
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
        
        /*
        final int PORT = 3612;
        final String hostname = "localhost";

        // setup TCP server on the specified port
        Thread thread = new Thread(new TCPServer(PORT));
        thread.start();
        

        MockTCPClient client = new MockTCPClient(hostname, PORT);

        Query query = new CheckOutBookQuery(false, " ", "054792822X", " ", " ", " ", " ", " ", " ");
        
        System.out.println("About to send " + query + " to server.");
        
        String reply = client.requestAndWait(query.toString());
        
        System.out.println("Just got " + reply + " from server");
        
        Query response = Query.buildRequest(reply);

        // stop the server
        thread.interrupt();
        thread.join();
*/
/*        
        //----------------------------------------------------------------------
        //This section is TO DELETE for later
        //for now: testing for database connection
        Dbwrapper db = Dbwrapper.getInstance();
        
        //Example of getting query results and printing them
        //Single result
        System.out.println("\nSearch for book with ISBN: 1234\n");
        Book book = db.SearchISBN("9780345453747");
        System.out.println(book.toString());
        
        //Multiple results
        System.out.println("\nSearch for all books with Author: Douglas Adams\n");
        ArrayList list = new ArrayList();
        list = db.SearchAuthor("Douglas Adams");
        int size = list.size();
        for(int i = 0; i < size; i++){
            list.get(i).toString();
        }
        
        //add a user to db
        //db.addUser("CadeG", "test", "no@fake.com", "Cade", "Gatewood", -1,1);
        
        //Getting user authentification from database
        //0 is no entry
        //1 is regular user
        //2 is librarian
        //3 is admin
        int temp = db.getAuthorization("bob", "pass");
        System.out.println("bob Auth: " + temp);
        
        //bob is in the db, Bob is not.  Lets see what happens
        //2 (the authorization of bob) is printed twice
        //This means that database queries appear not to be case sensitive
        //Which is a real security issue for passwords
        temp = db.getAuthorization("Bob", "pass");
        System.out.println("Bob Auth: " + temp);
        
        //failcase
        temp = db.getAuthorization("noone", "nothing");
        System.out.println("no one Auth: "+ temp);
        
        
        //ISBNs are unique ids so you can't input these more than once
        //Adding a new book to the repo
        Book bookthing = BookFinder.getBookFromGoogle("9780345453747");
        db.addBook(bookthing);
        
        Book book2 = BookFinder.getBookFromGoogle("0439136369");
        db.addBook(book2);
        //----------------------------------------------------------------------
*/
    }
}
