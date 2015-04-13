package com.team1.books;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This class will lookup a book by its ISBN on Google and return a Book object
 * representing that data.
 * 
 * @author Brandon
 *
 */
public class BookFinder {
    private static final String GOOGLE_API_URL = "https://www.googleapis.com/books/v1/volumes?q=isbn:";
    
    public static final String DEFAULT_VALUE = " ";

    /**
     * Will return a Book containing information found on Google for the given
     * ISBN
     * 
     * @param isbn
     *            - The ISBN of the book you want to look up
     * @return A book containing the data for the specified ISBN
     * @throws InvalidISBNException
     *             If the entered ISBN is of improper length, or is not
     *             registered on Google
     */
    public static Book getBookFromGoogle(String isbn) throws InvalidISBNException {
    	System.out.println("Entering getBookFromGoogle");
        if (isbn.length() != 10 && isbn.length() != 13)
            throw new InvalidISBNException("ISBN length not equal to 10 or 13.");
        System.out.println("After if");

        // Get the entire JSON book object
        JSONObject jsonBook = null;
        System.out.println("Before try");
        try {
        	System.out.println("in try");
            jsonBook = getJSONObjectFromGoogle(isbn);
        }
        catch (IOException e) {
        	System.out.println("In catch");
            e.printStackTrace();
        }
        System.out.println("Before check");
        if (jsonBook != null && jsonBook.has("totalItems") && jsonBook.getInt("totalItems") == 0)
            throw new InvalidISBNException("ISBN " + isbn + " not found.");
        System.out.println("After check");

        // Make several references to important spots in the JSON structure
        System.out.println("Before items");
        JSONArray items = (jsonBook != null && jsonBook.has("items")) ? jsonBook.getJSONArray("items") : null;
        System.out.println("Before volumeInfo");
        JSONObject volumeInfo = (items != null && items.length() != 0) ? items.getJSONObject(0).getJSONObject("volumeInfo") : null;
        System.out.println("Before authors");
        JSONArray authors = (volumeInfo != null && volumeInfo.has("authors")) ? volumeInfo.getJSONArray("authors") : null;
        System.out.println("Before categories");
        JSONArray categories = (volumeInfo != null && volumeInfo.has("categories")) ? volumeInfo.getJSONArray("categories") : null;
        System.out.println("Before imageLinks");
        JSONObject imageLinks = (volumeInfo != null && volumeInfo.has("imageLinks")) ? volumeInfo.getJSONObject("imageLinks"): null;
        System.out.println("After imageLinks");

        // Get the data from the JSON object, If an entry does not exist, return
        // ""
        System.out.println("Before title");
        String title = (volumeInfo != null && volumeInfo.has("title")) ? volumeInfo.getString("title") : DEFAULT_VALUE;
        System.out.println("Before author");
        String author = (authors != null && authors.length() != 0) ? authors.getString(0) : DEFAULT_VALUE;
        System.out.println("Before publisher");
        String publisher = (volumeInfo != null && volumeInfo.has("publisher")) ? volumeInfo.getString("publisher") : DEFAULT_VALUE;
        System.out.println("Before dataPublished");
        String datePublished = (volumeInfo != null && volumeInfo.has("publishedDate")) ? volumeInfo.getString("publishedDate") : DEFAULT_VALUE;
        System.out.println("Before genre");
        String genre = (categories != null && (categories.length() != 0)) ? categories.getString(0) : DEFAULT_VALUE;
        System.out.println("Before imageURL");
        String imageURL = (imageLinks != null && imageLinks.has("thumbnail")) ? imageLinks.getString("thumbnail") : DEFAULT_VALUE;
        System.out.println("After ImageURL");
        
        //TODO: add imageURL to Book
        System.out.println("Image URL = " + imageURL);
        
        // Return the book
        return new Book(isbn, title, author, publisher, datePublished, genre, imageURL);
    }

    private static JSONObject getJSONObjectFromGoogle(String isbn) throws IOException {
        URL google = new URL(GOOGLE_API_URL + isbn);

        URLConnection connection = google.openConnection();
        BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String total = "";
        String inputLine;

        while ((inputLine = input.readLine()) != null)
            total = total.concat(inputLine);
        input.close();

        return new JSONObject(total);
    }

    // Simple main for testing purposes
    public static void main(String[] args) throws InvalidISBNException {
        Book book;

        // Test if ISBN 10 works
        book = BookFinder.getBookFromGoogle("054792822X");
        System.out.println("Isbn:           " + book.isbn);
        System.out.println("Title:          " + book.title);
        System.out.println("Genre:          " + book.genre);
        System.out.println("Author:         " + book.author);
        System.out.println("Publisher:      " + book.publisher);
        System.out.println("Date Published: " + book.datePublished + "\n");

        // Test if ISBN 13 works
        book = BookFinder.getBookFromGoogle("9780345453747");
        System.out.println("Isbn:           " + book.isbn);
        System.out.println("Title:          " + book.title);
        System.out.println("Genre:          " + book.genre);
        System.out.println("Author:         " + book.author);
        System.out.println("Publisher:      " + book.publisher);
        System.out.println("Date Published: " + book.datePublished);

        // Test if exception is thrown for ISBNs of improper length
        try {
            book = BookFinder.getBookFromGoogle("123456789");
        }
        catch (InvalidISBNException e) {
            e.printStackTrace();
        }
        // Test if exception is thrown for invalid ISBNs
        try {
            book = BookFinder.getBookFromGoogle("1234567890");
        }
        catch (InvalidISBNException e) {
            e.printStackTrace();
        }
    }
}