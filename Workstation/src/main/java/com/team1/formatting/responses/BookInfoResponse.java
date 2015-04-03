package com.team1.formatting.responses;


import java.util.ArrayList;

import com.team1.books.Book;

public class BookInfoResponse extends Response
{
    public ArrayList<Book> books = null;
    public static final String bookBreak = ";#;";

//  public boolean filled = false;
    
    public BookInfoResponse()
    {
    	super(false,"0");
    	books = null;
    }
    
    public BookInfoResponse(boolean wasSuccessful, String sessionID, int numBooks, String strBooks)
    {
        super(wasSuccessful, sessionID);
        
        String[] bookList = strBooks.split(bookBreak);
        for (int i = 0; i < numBooks; i++)
        {
        	System.out.println("Trying to make this a book: " + bookList[i]);
            books.add(new Book(bookList[i]));
        }
        
    }
    
    private static boolean isValid(String string)
    {
        return string != null && !string.equals("");
    }
    
    //Override of toString. Method to return the object information in the form of a string.
    @Override
    public String toString() {
        String s;
        int numBooks = 0;
        if (books.isEmpty()) System.out.print("\nbooks is empty, execute bookInfoQuery before attempting to read Response Object");
        else numBooks = books.size();
        
        if (this.wasSuccessful) s = "true";
        else s = "false";
        
        
        String msg = "BookInfoResponse" + DELIMITER + s + DELIMITER + sessionID + DELIMITER + numBooks + DELIMITER;
        
        for (int i = 0; i < numBooks; i++)
        {
            msg = msg.concat(books.get(i).getSerialized());
            msg = msg.concat(bookBreak);
        }
        
        return msg;
    }
    
}
