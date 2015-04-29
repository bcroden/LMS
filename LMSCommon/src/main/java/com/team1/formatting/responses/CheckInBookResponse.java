package com.team1.formatting.responses;

import java.util.ArrayList;

import com.team1.books.Book;

public class CheckInBookResponse extends Response
{
	public static final String HEADER = "CheckInBookResponse";
	
	public static final String bookBreak = ";#;";
    public String userName;
    public String fines;
    public ArrayList<Book> books = null;
	
    public CheckInBookResponse(boolean wasSuccessful, String sessionID, String userName, String fines, int numBooks, String strBooks)
    {
        super(wasSuccessful, sessionID);
        this.userName = userName;
        this.fines = fines;
        System.out.println("The number of books: " + numBooks);
        books = new ArrayList<Book>();
        
        String[] bookList = strBooks.split(bookBreak);
        System.out.println("The list thinks there are: " + bookList.length);
        for (int i = 0; i < numBooks; i++)
        {
        	System.out.println("YOU SHOULDN'T BE HERE");
            books.add(new Book(bookList[i]));
        }
    }
    
    public CheckInBookResponse() 
    {
		super(false, "0");
	}
    
    //Override of toString. Method to return the object information in the form of a string.
    @Override
    public String toString() {
        String s;
        
        int numBooks = 0;
        numBooks = books.size();
        
        if (wasSuccessful) s = "true";
        else s = "false";
        String msg = HEADER + DELIMITER + s + DELIMITER + sessionID + DELIMITER + userName + DELIMITER + fines + DELIMITER + numBooks + DELIMITER + " ";
        
        for (int i = 0; i < numBooks; i++)
        {
            msg = msg.concat(books.get(i).getSerialized());
            msg = msg.concat(bookBreak);
        }
        
        return msg;
    }
}