package com.team1.formatting;

public class BookInfoQuery extends LibrarianQuery
{
    public BookInfoQuery(String msg) {
        super(msg);
        // TODO Auto-generated constructor stub
    }
    

    //book information
    public String isbn;
    public String title;
    public String author;
    
    //variable for if a book is checked out/in
    public String availability;
    
    
}