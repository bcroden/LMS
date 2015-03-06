public class PatronInfoQuery extends LibrarianQuery
{
    //patron first name and last name
    public static final String fName, lName;
    
    //variable for what kind of patron query is being sent: fine lookup, fine payment, fine 
    public static final String action;
    
    //Variables used to either check out books or tell the librarian what books they have checked out
    //MAX_CHECKOUT number to be decided
    public static final String[MAX_CHECKOUT] booksOut;
    public static final int numBooksOut;
    
    //variables used to tell the database how many books are being returned and which books they are
    public static final int numBooksIn;
    public static final String bookCheckIn;
    
    //variables for: fines owed, payment amount, or fine addition amount
    public static final float fines;
    public static final float payment;
    public static final float fineAddition;
}