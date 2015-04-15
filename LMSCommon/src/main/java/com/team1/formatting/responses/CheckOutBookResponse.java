package com.team1.formatting.responses;

import java.util.ArrayList;

import com.team1.books.Book;

public class CheckOutBookResponse extends Response
{
	public static final String HEADER = "CheckOutBookResponse";
	
	public static final String bookBreak = ";#;";
    public String userName;
    public String fines;
    public ArrayList<Book> books = null;
    
    public CheckOutBookResponse(boolean wasSuccessful, String sessionID, String userName, String fines, int numBooks, String strBooks)
    {
        super(wasSuccessful, sessionID);
        this.userName = userName;
        this.fines = fines;
        
        this.books = new ArrayList<Book>();
        
        String[] bookList = strBooks.split(bookBreak);
        for (int i = 0; i < bookList.length; i++)
        {
        	System.out.println("\n\nBooklist info\n"+bookList[i]);
            this.books.add(new Book(bookList[i]));
        }
        
        for (int i = 0; i < books.size(); i++)
        {
        	System.out.println(books.get(i));
        }
    }
    
    public CheckOutBookResponse() 
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