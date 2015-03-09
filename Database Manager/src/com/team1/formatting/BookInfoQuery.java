package com.team1.formatting;

public class BookInfoQuery extends LibrarianQuery
{
    public BookInfoQuery(String isbn, String title, String author, String publisher, String datePublished, String genre, String availability) {
        // TODO Auto-generated constructor stub
        super("");
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.datePublished = datePublished;
        this.genre = genre;
        this.availability = availability;
    }
    
    //delimeter for building toString
    public String d = ";";

    //book information
    public String isbn;
    public String title;
    public String author;
    public String publisher;
    public String datePublished;
    public String genre;

    
    //variable for if a book is checked out/in
    public String availability;
    
    @Override
    public String toString() {
        String msg = "BookInfoQuery;"+isbn+d+title+d+author+d+publisher+d+datePublished+d+genre+d+availability;
        return msg;
    }

}

