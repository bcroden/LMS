package com.team1.formatting.responses;

import java.sql.SQLException;
import java.util.ArrayList;

import com.team1.authentication.Authentication;
import com.team1.books.Book;
import com.team1.books.BookFinder;
import com.team1.books.InvalidISBNException;
import com.team1.db.Dbwrapper;
import com.team1.formatting.queries.*;

public class CheckInBookResponse extends Response
{
	public static final String bookBreak = ";$;";
    public String userName;
    public String fines;
    public ArrayList<Book> books = null;
	
    public CheckInBookResponse(boolean wasSuccessful, String sessionID, String userName, String fines, int numBooks, String strBooks)
    {
        super(wasSuccessful, sessionID);
        this.userName = userName;
        this.fines = fines;
        String[] bookList = strBooks.split(bookBreak);
        for (int i = 0; i < numBooks; i++)
        {
            books.add(new Book(bookList[i]));
        }
    }
    
    public CheckInBookResponse() 
    {
		super(false, "0");
	}

	public void executeCheckInBookQuery(CheckInBookQuery query)
    {
		//Check users authentication
		int status = Authentication.getInstance().authenticate(query);
        
        if (status == 0 || status == 1) wasSuccessful = false;
        else if (status == 2 || status == 3)
        {
            try
            {
                Dbwrapper.getInstance().CheckIn(query.isbn,query.userID);
            	userName = query.userID;
            	fines = ""+Dbwrapper.getInstance().getBalance(query.userID);
            	String msg = Dbwrapper.getInstance().getBooksOut(query.userID);
            	String[] str = msg.split(",");
            	for(int i = 0; i < str.length; i++)
            	{
            		books.add(BookFinder.getBookFromGoogle(str[i]));
            	}
                wasSuccessful = true;
            }
            catch(SQLException | InvalidISBNException e)
            {
                e.printStackTrace();
                wasSuccessful = false;
            }
            sessionID = query.sessionID;
        }
        else
        {
            wasSuccessful = false;
            sessionID = query.sessionID;
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
        String msg = "CheckInBookResponse" + DELIMITER + s + DELIMITER + sessionID + DELIMITER + userName + DELIMITER + fines + DELIMITER + numBooks + DELIMITER;
        
        for (int i = 0; i < numBooks; i++)
        {
            msg.concat(books.get(i).getSerialized());
            msg.concat(bookBreak);
        }
        
        return msg;
    }
}