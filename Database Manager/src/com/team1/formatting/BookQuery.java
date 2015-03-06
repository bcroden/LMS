package com.team1.formatting;

public class BookQuery extends LibrarianQuery
{
    public BookQuery(String msg) {
        super(msg);
        // TODO Auto-generated constructor stub
    }

    //variable for what kind of book query the user wants: add a book, look up a book, check out a book, return a book (last two may be handled via the patron info query)
    public String action;
    

    //book information for either a look up or book add in. 
    public String isbn;
    public String title;
    public String author;
    
    //variable for if a book is checked out/in
    public String availability;
    
    
}