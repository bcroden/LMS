package com.team1.formatting.responses;

package com.team1.formatting;

import java.sql.SQLException;
import java.util.ArrayList;
import com.team1.books.Book;
import com.team1.books.InvalidISBNException;
import com.team1.db.Dbwrapper;

public class BookInfoResponse extends Response
{
    public ArrayList<Book> books = null;
    public static final String bookBreak = ";#;";

//  public boolean filled = false;
    
    
    public BookInfoResponse(boolean wasSuccessful, int numBooks, String strBooks)
    {
        this.wasSuccessful = wasSuccessful;
        
        String[] bookList = strBooks.split(bookBreak);
        for (int i = 0; i < numBooks; i++)
        {
            books.add(new Book(bookList[i]));
        }
        
    }
    
    public static void buildResponse(BookInfoQuery query)
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
                this.wasSuccessful = true;
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    //Override of toString. Method to return the object information in the form of a string.
    @Override
    public String toString() {
        String s, sBooks;
        int numBooks;
        if (books.isEmpty()) printf("\nbooks is empty, execute bookInfoQuery before attempting to read Response Object");
        else numBooks = books.size();
        
        if (this.wasSuccessful) s = "true";
        else s = "false";
        
        
        String msg = "BookInfoResponse" + this.DELIMITER + s + this.DELIMITER + numBooks;
        
        for (int i = 0; i < numBooks; i++)
        {
            msg.concat(books.get(i).getSerialized());
            msg.concat(this.bookBreak);
        }
        
        return msg;
    }
    
}
