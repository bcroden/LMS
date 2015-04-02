package com.team1.formatting.queries;

public class AddBookQuery extends LibrarianQuery
{
    public Book book;
    public String isbn;
    
    public AddBookQuery(Book book)
    {
        this.book = book;
    }
    
    public AddBookQuery(String isbn)
    {
        this.isbn = isbn;
    }
    
    //Add toString method
}