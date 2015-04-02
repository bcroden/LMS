package com.team1.formatting.responses;

import java.sql.SQLException;
import java.util.ArrayList;
import com.team1.books.Book;
import com.team1.books.InvalidISBNException;
import com.team1.db.Dbwrapper;
import com.team1.authentication.Authentication;

public class LogInResponse extends Response
{
    public int status;
    
    public LogInResponse(boolean wasSuccessful)
    {
        this.wasSuccessful = wasSuccessful;
    }
    
    public static void executeLogInResponse(LoginQuery query)
    {

        //execute a login
        status = Authentication.authenticate(query);
        
        if (status == 0) wasSuccessful = false;
        else if (status >= 1 && status <= 3) wasSuccessful = true;
        else
        {
            wasSuccessful = false;
            printf("unexpected return value from authenticate...\n");
        }
        
/*        try
        {
            System.out.println("Pre call");
            Dbwrapper.getInstance().CheckOut(query.isbn);
            this.wasSuccessful = true;
            System.out.println("Post call");
        }
        catch(SQLException | InvalidISBNException e)
        {
            e.printStackTrace();
            this.wasSuccessful = false;
        }
        return;*/
    }
    
    //Override of toString. Method to return the object information in the form of a string.
    @Override
    public String toString() {
        String s;
        if (this.wasSuccessful) s = "true";
        else s = "false";
        String msg = "LogInResponse" + this.DELIMITER + s;
        return msg;
    }
}