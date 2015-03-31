package com.team1.formatting.queries;

public class BookInfoQuery extends LibrarianQuery
{
    public BookInfoQuery(Boolean wasSuccessful, String sessionID, String isbn, String title, String author, String publisher, String datePublished, String genre, String availability) {
        // TODO Auto-generated constructor stub
        super(wasSuccessful, sessionID);
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.datePublished = datePublished;
        this.genre = genre;
        this.availability = availability;
    }
    
    
    //Methods to set single variables
    public BookInfoQuery searchByISBN(String isbn) {
        this.isbn = isbn;
        return this;
    }
    
    public BookInfoQuery searchByTitle(String title) {
        this.title = title;
        return this;
    }
    
    public BookInfoQuery searchByAuthor(String author) {
        this.author = author;
        return this;
    }
    
    public BookInfoQuery searchByPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }
    
    public BookInfoQuery searchByDatePublished(String datePublished) {
        this.datePublished = datePublished;
        return this;
    }
    
    public BookInfoQuery searchByGenre(String genre) {
        this.genre = genre;
        return this;
    }
    
    public BookInfoQuery searchByAvailability(String availability) {
        this.availability = availability;
        return this;
    }
    
    
    
    //delimeter for building toString
//    private String d = ";";

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
        String s;
        if (this.wasSuccessful) s = "true";
        else s = "false";
        String msg = "BookInfoQuery;"+s+this.DELIMITER+sessionID+this.DELIMITER+isbn+this.DELIMITER+title+this.DELIMITER+author+this.DELIMITER+publisher+this.DELIMITER+datePublished+this.DELIMITER+genre+this.DELIMITER+availability;
        return msg;
    }
    
    public static void main(String[] args) {
        BookInfoQuery query = new BookInfoQuery(false, "0", "054792822X", " ", " ", " ", " ", " ", " ");
        System.out.println(query);
        String toString = query.toString();
        Query query2 = Query.buildRequest(toString);
        System.out.println(query2);
    }
}

