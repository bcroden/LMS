package com.team1.books;

/**
 * A basic container class for a book.
 * Feel free to add any additional fields you think are necessary or that I have missed.
 * 
 * @author Brandon
 *
 */
public class Book {
    //Any string will do for a delimiter, but it should probably be different from the Query Delimiter
    public static final String DELIMITER = "@@@";
    public static final String HEADER = "BOOK_DATA";
    
    public final String isbn;
    public final String title;
    public final String author;
    public final String publisher;
    public final String datePublished;

    public Book(String isbn, String title, String author, String publisher, String datePublished) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.datePublished = datePublished;
    }
    
    public Book(String serializedString) {
        String[] data = serializedString.split(DELIMITER);
        
        this.isbn = data[1];
        this.title = data[2];
        this.author = data[3];
        this.publisher = data[4];
        this.datePublished = data[5];
    }
    
    @Override
    public String toString() {
        return HEADER + DELIMITER + isbn + DELIMITER + title + DELIMITER + author + DELIMITER + publisher + DELIMITER + datePublished;
    }
    
    //Basic main for testing purposes
    public static void main(String[] args) throws InvalidISBNException {
        Book book = BookFinder.getBookFromGoogle("054792822X");
        
        System.out.println("Unserialized Book.");
        System.out.println("Isbn:           " + book.isbn);
        System.out.println("Title:          " + book.title);
        System.out.println("Author:         " + book.author);
        System.out.println("Publisher:      " + book.publisher);
        System.out.println("Date Published: " + book.datePublished + "\n");
        
        String serializedBook = book.toString();
        
        System.out.println("Serialized Book.");
        System.out.println(serializedBook + "\n");
        
        Book book2 = new Book(serializedBook);
        
        System.out.println("Deserialized Book.");
        System.out.println("Isbn:           " + book2.isbn);
        System.out.println("Title:          " + book2.title);
        System.out.println("Author:         " + book2.author);
        System.out.println("Publisher:      " + book2.publisher);
        System.out.println("Date Published: " + book2.datePublished + "\n");
    }
}