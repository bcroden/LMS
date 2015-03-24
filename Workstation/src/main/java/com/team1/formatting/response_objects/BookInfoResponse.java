package com.team1.formatting;

import java.sql.SQLException;
import java.util.ArrayList;
import com.team1.books.Book;
import com.team1.books.InvalidISBNException;
import com.team1.db.Dbwrapper;

public class BookInfoResponse extends Response
{
    public ArrayList<Book> books = null;
    public boolean filled = false;
    
    public BookInfoResponse()
    {
//        this.books = books;
    }
    
    public static void buildResponse(BookInfoQuery query)
    {
        try
        {
            if(isValid(query.isbn))
            {
                books = new ArrayList<Book>();
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
                filled = true;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    
    
}
