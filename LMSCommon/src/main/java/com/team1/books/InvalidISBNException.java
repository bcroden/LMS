package com.team1.books;

/**
 * An InvalidISBNException is thrown when an ISBN not the right size, or the
 * ISBN is not found on Google.
 * 
 * @author Brandon
 *
 */
public class InvalidISBNException extends Exception {
    private static final long serialVersionUID = 0;

    public InvalidISBNException() {
    	super();
    }

    public InvalidISBNException(String message) {
        super(message);
    }
}