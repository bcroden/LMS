package com.team1.books;

/**
 * A basic container class for a book. Feel free to add any additional fields
 * you think are necessary or that I have missed.
 * 
 * @author Brandon
 *
 */
public class Book {
    // Any string will do for a delimiter, but it should probably be different
    // from the Query Delimiter
    private static final String DELIMITER = "@@@";
    private static final String HEADER = "BOOK_DATA";

    public final String isbn;
    public final String title;
    public final String author;
    public final String publisher;
    public final String datePublished;
    public final String genre;

    /**
     * Creates a book.
     */
    public Book(String isbn, String title, String author, String publisher, String datePublished, String genre) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.datePublished = datePublished;
        this.genre = genre;
    }

    /**
     * Creates a book from a serialized String.
     * 
     * NOTE: This is not yet smart enough to check if the string is valid. I
     * will add this when the specification for this class is "finalized".
     * 
     * @param serializedString
     *            - The String returned by the getSerialized() method.
     */
    public Book(String serializedString) {
        String[] data = serializedString.split(DELIMITER);

        this.isbn = data[1];
        this.title = data[2];
        this.author = data[3];
        this.publisher = data[4];
        this.datePublished = data[5];
        this.genre = data[6];
    }

    /**
     * Serializes the book object into a format that can be recreated by the
     * constructor.
     * 
     * @return A String representing the serialized form of a book.
     */
    public String getSerialized() {
        return HEADER + DELIMITER + isbn + DELIMITER + title + DELIMITER + author + DELIMITER + publisher + DELIMITER + datePublished
                + DELIMITER + genre;
    }

    @Override
    public String toString() {
        return "Book data:" + "\nISBN: " + isbn + "\nTitle: " + title + "\nAuthor: " + author + "\nPublisher: " + publisher
                + "\nPublish Date: " + datePublished + "\nGenre: " + genre;
    }
}