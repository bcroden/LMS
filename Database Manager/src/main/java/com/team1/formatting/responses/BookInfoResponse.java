package com.team1.formatting.responses;


import java.sql.SQLException;
import java.util.ArrayList;
import com.team1.books.Book;
import com.team1.books.InvalidISBNException;
import com.team1.db.Dbwrapper;
import com.team1.formatting.queries.*;

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
            books.add(new Book(bookList[i]));
        }
        
    }
    
    public void executeBookInfoQuery(BookInfoQuery query)
    {
        try
        {
            if(isValid(query.isbn))
            {
                books.add(Dbwrapper.getInstance().SearchISBN(query.isbn));
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
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        sessionID = query.sessionID;
        return;
    }
    
    
    private static boolean isValid(String string)
    {
        return string != null && !string.equals("");
    }
    
    //Override of toString. Method to return the object information in the form of a string.
    @Override
    public String toString() {
        String s, sBooks;
        int numBooks = 0;
        if (books.isEmpty()) System.out.print("\nbooks is empty, execute bookInfoQuery before attempting to read Response Object");
        else numBooks = books.size();
        
        if (this.wasSuccessful) s = "true";
        else s = "false";
        
        
        String msg = "BookInfoResponse" + DELIMITER + s + DELIMITER + sessionID + DELIMITER + numBooks;
        
        for (int i = 0; i < numBooks; i++)
        {
            msg.concat(books.get(i).getSerialized());
            msg.concat(bookBreak);
        }
        
        return msg;
    }
    
}
