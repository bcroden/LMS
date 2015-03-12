package com.team1.network;

import java.sql.SQLException;
import java.util.ArrayList;

import com.team1.books.Book;
import com.team1.books.InvalidISBNException;
import com.team1.db.Dbwrapper;
import com.team1.formatting.BookInfoQuery;
import com.team1.formatting.CheckInBookQuery;
import com.team1.formatting.CheckOutBookQuery;
import com.team1.formatting.Query;

class QueryUtils
{    
    public static Query executeQuery(Query query)
    {
        if(query instanceof BookInfoQuery)
            return executeBookInfoQuery((BookInfoQuery) query);
        if(query instanceof CheckInBookQuery)
            return executeCheckInBookQuery((CheckInBookQuery) query);
        if(query instanceof CheckOutBookQuery)
            return executeCheckOutBookQuery((CheckOutBookQuery) query);
        
        return new Query(false);
    }
    
    public static Query executeBookInfoQuery(BookInfoQuery query)
    {
        try
        {
            ArrayList<Book> books = null;
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
                return bookToQuery(books.get(0), query.sessionID);
        }
        catch(SQLException e)
        {
            
        }
        
        query.wasSuccessful = false;
        return query;
    }
    public static Query executeCheckInBookQuery(CheckInBookQuery query)
    {
        try
        {
            Dbwrapper.getInstance().CheckIn(query.isbn);
            query.wasSuccessful = true;
        }
        catch(SQLException | InvalidISBNException e)
        {
            query.wasSuccessful = false;
        }
        
        return query;
    }
    public static Query executeCheckOutBookQuery(CheckOutBookQuery query)
    {
        try
        {
            System.out.println("Pre call");
            Dbwrapper.getInstance().CheckOut(query.isbn);
            query.wasSuccessful = true;
            System.out.println("Post call");
        }
        catch(SQLException | InvalidISBNException e)
        {
            e.printStackTrace();
            query.wasSuccessful = false;
        }
        
        return query;
    }
    
    private static boolean isValid(String string)
    {
        return string != null && !string.equals("");
    }
    private static Query bookToQuery(Book book, String sessionID) throws SQLException
    {
        boolean successful = book != null;

        if(successful)
            return new BookInfoQuery(successful, sessionID, book.isbn, book.title, book.author, book.publisher, book.datePublished, book.genre, Integer.toString(Dbwrapper.getInstance().GetAvailable(book.isbn)));
        
        return new BookInfoQuery(false, sessionID, "", "", "", "", "", "", "");
    }
}