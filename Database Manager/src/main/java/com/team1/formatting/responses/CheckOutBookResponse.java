package com.team1.formatting.responses;

import java.sql.SQLException;
import java.util.ArrayList;

import com.team1.authentication.Authentication;
import com.team1.books.Book;
import com.team1.books.BookFinder;
import com.team1.books.InvalidISBNException;
import com.team1.db.Dbwrapper;
import com.team1.formatting.queries.*;

public class CheckOutBookResponse extends Response
{
	public static final String bookBreak = ";$;";
    public String userName;
    public String fines;
    public ArrayList<Book> books = null;
    
    public CheckOutBookResponse(boolean wasSuccessful, String sessionID, String userName, String fines, int numBooks, String strBooks)
    {
        super(wasSuccessful, sessionID);
        this.userName = userName;
        this.fines = fines;
        
        books = new ArrayList<Book>();
        
        String[] bookList = strBooks.split(bookBreak);
        for (int i = 0; i < numBooks; i++)
        {
            books.add(new Book(bookList[i]));
        }
    }
    
    public CheckOutBookResponse() 
    {
		super(false, "0");
	}

	public void executeCheckOutBookQuery(CheckOutBookQuery query)
    {
		//Check users authentication
		int temp = Authentication.getInstance().authenticate(query);
		sessionID = Integer.toString(temp);
		int status = Authentication.getInstance().getLevel(temp);
		
		books = new ArrayList<Book>();
        
        if (status == 0 || status == 1) wasSuccessful = false;
        else if (status == 2 || status == 3)
        {
            try
            {
                System.out.println("Pre call");
                Dbwrapper.getInstance().CheckOut(query.isbn,query.userID);
                userName = query.userID;
            	fines = ""+Dbwrapper.getInstance().getBalance(query.userID);
            	String msg = Dbwrapper.getInstance().getBooksOut(query.userID);
            	String[] str = msg.split(",");
            	for(int i = 0; i < str.length; i++)
            	{
            		books.add(BookFinder.getBookFromGoogle(str[i]));
            	}
                wasSuccessful = true;
                System.out.println("Post call");
            }
            catch(SQLException | InvalidISBNException e)
            {
                e.printStackTrace();
                wasSuccessful = false;
            }
        }
        else
        {
            wasSuccessful = false;
            System.out.print("unexpected return value from authenticate...\n");
        }
        
        return;
    }
    
    //Override of toString. Method to return the object information in the form of a string.
    @Override
    public String toString() {
    	String s;
        
        int numBooks = 0;
        numBooks = books.size();
        
        if (wasSuccessful) s = "true";
        else s = "false";
        String msg = "CheckOutBookResponse" + DELIMITER + s + DELIMITER + sessionID + DELIMITER + userName + DELIMITER + fines + DELIMITER + numBooks + DELIMITER;
        
        for (int i = 0; i < numBooks; i++)
        {
            msg = msg.concat(books.get(i).getSerialized());
            msg = msg.concat(bookBreak);
        }
        
        return msg;
    }
}