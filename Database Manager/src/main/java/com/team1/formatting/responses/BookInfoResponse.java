package com.team1.formatting.responses;

import java.sql.SQLException;
import java.util.ArrayList;

import com.team1.authentication.Authentication;
import com.team1.books.Book;
import com.team1.db.Dbwrapper;
import com.team1.formatting.queries.BookInfoQuery;

public class BookInfoResponse extends Response
{
	public static final String HEADER = "BookInfoResponse";
	
    public ArrayList<Book> books = null;
    public static final String bookBreak = ";#;";

//  public boolean filled = false;
    
    public BookInfoResponse()
    {
    	super(false,"0");
    	books = new ArrayList<Book>();
    }
    
    public BookInfoResponse(boolean wasSuccessful, String sessionID, int numBooks, String strBooks)
    {
        super(wasSuccessful, sessionID);
        
        books = new ArrayList<Book>();
        
        String[] bookList = strBooks.split(bookBreak);
        for (int i = 0; i < numBooks; i++)
        {
            books.add(new Book(bookList[i]));
        }
        
    }
    
    public void executeBookInfoQuery(BookInfoQuery query)
    {
    	//Check users authentication
		int sessionId = Authentication.getInstance().authenticate(query);
		int status = Authentication.getInstance().getLevel(sessionId);
        
		System.out.println("status = " + status);
		
		books = new ArrayList<Book>();
		
        if (status == 0) wasSuccessful = false;
        else if (status == 1 || status == 2 || status == 3)
        {
			try
	        {
				System.out.println("start try block");
	            if(isValid(query.isbn))
	            {
	            	System.out.println("in isbn search");
	                books.add(Dbwrapper.getInstance().SearchISBN(query.isbn));
	                System.out.println("after isbn search");
	            }
	            else if(isValid(query.title))
	            {
	                books = Dbwrapper.getInstance().SearchTitle(query.title);
	            }
	            else if(isValid(query.author))
	            {
	                books = Dbwrapper.getInstance().SearchAuthor(query.author);
	            }
	            else if(isValid(query.genre))
	            {
	                books = Dbwrapper.getInstance().SearchGenre(query.genre);
	            }
	            else if(isValid(query.datePublished))
	            {
	                books = Dbwrapper.getInstance().SearchPublisher(query.publisher);
	            }
	            
	            if(books != null)
	                wasSuccessful = true;  
	            System.out.println("end try block");
	        }
	        catch(SQLException e)
	        {
	            e.printStackTrace();
	        }
			 System.out.println("before toString");
	        sessionID = Integer.toString(sessionId);
	        System.out.println("after bookinfo");
        }
        else
        {
        	 System.out.println("in else");
            wasSuccessful = false;
            sessionID = Integer.toString(sessionId);
            System.out.print("unexpected return value from authenticate...\n");
            System.out.println("after else");
        }
        
        return;
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
        
        
        String msg = HEADER + DELIMITER + s + DELIMITER + sessionID + DELIMITER + numBooks + DELIMITER;
        
        System.out.println("numBooks = " + numBooks);
        
        for (int i = 0; i < numBooks; i++)
        {
            msg = msg.concat(books.get(i).getSerialized());
            System.out.println("Book = " + books.get(i).getSerialized());
            msg = msg.concat(bookBreak);
        }
        
        System.out.println("msg = " + msg);
        
        return msg;
    }
    
}
