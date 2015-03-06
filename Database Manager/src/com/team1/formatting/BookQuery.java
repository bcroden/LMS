public class BookQuery extends LibrarianQuery
{
    //variable for what kind of book query the user wants: add a book, look up a book, check out a book, return a book (last two may be handled via the patron info query)
    public static final String action;
    

    //book information for either a look up or book add in. 
    public static final String isbn;
    public static final String title;
    public static final String author;
    
    //variable for if a book is checked out/in
    public static final String availability;
    
    
}