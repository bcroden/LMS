package com.team1.formatting;

import java.sql.SQLException;
import java.util.ArrayList;

import com.team1.authentication.Authentication;
import com.team1.books.Book;
import com.team1.books.BookFinder;
import com.team1.books.InvalidISBNException;
import com.team1.db.Dbwrapper;
import com.team1.formatting.queries.AddBookQuery;
import com.team1.formatting.queries.AddLibrarianQuery;
import com.team1.formatting.queries.BookInfoQuery;
import com.team1.formatting.queries.CheckInBookQuery;
import com.team1.formatting.queries.CheckOutBookQuery;
import com.team1.formatting.queries.LoginQuery;
import com.team1.formatting.queries.PasswordChangeQuery;
import com.team1.formatting.queries.PayFinesQuery;
import com.team1.formatting.queries.Query;
import com.team1.formatting.queries.RemoveLibrarianQuery;
import com.team1.formatting.queries.SetFineQuery;
import com.team1.formatting.responses.AddBookResponse;
import com.team1.formatting.responses.AddLibrarianResponse;
import com.team1.formatting.responses.BookInfoResponse;
import com.team1.formatting.responses.CheckInBookResponse;
import com.team1.formatting.responses.CheckOutBookResponse;
import com.team1.formatting.responses.LogInResponse;
import com.team1.formatting.responses.PasswordChangeResponse;
import com.team1.formatting.responses.PayFinesResponse;
import com.team1.formatting.responses.RemoveLibrarianResponse;
import com.team1.formatting.responses.Response;
import com.team1.formatting.responses.SetFineResponse;

public class QueryUtils {
    public static boolean isValid(String string)
    {
        return string != null && !string.equals("");
    }
	
	public static AddBookResponse executeAddBookQuery(AddBookQuery query) throws InvalidISBNException, SQLException
    {
		AddBookResponse response = new AddBookResponse();
		
		//Check users authentication
		int temp = Authentication.getInstance().authenticate(query);
		response.sessionID = Integer.toString(temp);
		int status = Authentication.getInstance().getLevel(temp);
        
        if (status == 0 || status == 1) response.wasSuccessful = false;
        else if (status == 2 || status == 3)
        {
        	//build book object from isbn
            Book book = BookFinder.getBookFromGoogle(query.isbn);
            Dbwrapper.getInstance().addBook(book, query.numCopies);
            
            response.wasSuccessful = true;
        }
        else
        {
        	response.wasSuccessful = false;
            System.out.print("unexpected return value from authenticate...\n");
        }
		
        return response;
    }
	
    public static BookInfoResponse executeBookInfoQuery(BookInfoQuery query)
    {
    	BookInfoResponse response = new BookInfoResponse();
    	
    	//Check users authentication
		int sessionId = Authentication.getInstance().authenticate(query);
		int status = Authentication.getInstance().getLevel(sessionId);
        
		System.out.println("status = " + status);
		
		response.books = new ArrayList<Book>();
		
        if (status == 0) response.wasSuccessful = false;
        else if (status == 1 || status == 2 || status == 3)
        {
			try
	        {
				System.out.println("start try block");
	            if(isValid(query.isbn))
	            {
	            	System.out.println("in isbn search");
	            	response.books.add(Dbwrapper.getInstance().SearchISBN(query.isbn));
	                System.out.println("after isbn search");
	            }
	            else if(isValid(query.title))
	            {
	            	response.books = Dbwrapper.getInstance().SearchTitle(query.title);
	            }
	            else if(isValid(query.author))
	            {
	            	response.books = Dbwrapper.getInstance().SearchAuthor(query.author);
	            }
	            else if(isValid(query.genre))
	            {
	            	response.books = Dbwrapper.getInstance().SearchGenre(query.genre);
	            }
	            else if(isValid(query.datePublished))
	            {
	            	response.books = Dbwrapper.getInstance().SearchPublisher(query.publisher);
	            }
	            
	            if(response.books != null)
	            	response.wasSuccessful = true;  
	            System.out.println("end try block");
	        }
	        catch(SQLException e)
	        {
	            e.printStackTrace();
	        }
			 System.out.println("before toString");
			 response.sessionID = Integer.toString(sessionId);
	        System.out.println("after bookinfo");
        }
        else
        {
        	 System.out.println("in else");
        	response.wasSuccessful = false;
        	response.sessionID = Integer.toString(sessionId);
            System.out.print("unexpected return value from authenticate...\n");
            System.out.println("after else");
        }
        
        return response;
    }
    
	public static CheckInBookResponse executeCheckInBookQuery(CheckInBookQuery query)
    {
		CheckInBookResponse response = new CheckInBookResponse();
		
		//Check users authentication
		int temp = Authentication.getInstance().authenticate(query);
		response.sessionID = Integer.toString(temp);
		int status = Authentication.getInstance().getLevel(temp);
		System.out.println("After after get level");
		response.books = new ArrayList<Book>();
        
        if (status == 0 || status == 1) response.wasSuccessful = false;
        else if (status == 2 || status == 3)
        {
            try
            {
            	System.out.println("Trying really really hard");
                Dbwrapper.getInstance().CheckIn(query.isbn,query.userID);
                System.out.println("Somehow we got through checkin");
                response.userName = query.userID;
                response.fines = ""+Dbwrapper.getInstance().getBalance(query.userID);
            	System.out.println("Fines: " + response.fines);
            	String msg = Dbwrapper.getInstance().getBooksOut(query.userID);
            	String[] str = msg.split(",");
            	for(int i = 0; i < str.length; i++)
            	{
            		response.books.add(BookFinder.getBookFromGoogle(str[i]));
            	}
            	
            	response.wasSuccessful = true;
            }
            catch(SQLException | InvalidISBNException e)
            {
                e.printStackTrace();
                response.wasSuccessful = false;
            }
        }
        else
        {
        	response.wasSuccessful = false;
            System.out.print("unexpected return value from authenticate...\n");
        }
        
        return response;
    }
	
	public static CheckOutBookResponse executeCheckOutBookQuery(CheckOutBookQuery query)
    {
		CheckOutBookResponse response = new CheckOutBookResponse();
		
		//Check users authentication
		int temp = Authentication.getInstance().authenticate(query);
		response.sessionID = Integer.toString(temp);
		int status = Authentication.getInstance().getLevel(temp);
		
		response.books = new ArrayList<Book>();
        
        if (status == 0 || status == 1) response.wasSuccessful = false;
        else if (status == 2 || status == 3)
        {
            try
            {
                System.out.println("Pre call");
                Dbwrapper.getInstance().CheckOut(query.isbn,query.userID);
                response.userName = query.userID;
                response.fines = ""+Dbwrapper.getInstance().getBalance(query.userID);
            	
            	String msg = Dbwrapper.getInstance().getBooksOut(query.userID);
            	String[] str = msg.split(",");
            	for(int i = 0; i < str.length; i++)
            	{
            		response.books.add(BookFinder.getBookFromGoogle(str[i]));
            	}
            	
                response.wasSuccessful = true;
                System.out.println("Post call");
            }
            catch(SQLException | InvalidISBNException e)
            {
                e.printStackTrace();
                response.wasSuccessful = false;
            }
        }
        else
        {
        	response.wasSuccessful = false;
            System.out.print("unexpected return value from authenticate...\n");
        }
        
        return response;
    }
	
    public static LogInResponse executeLogInQuery(LoginQuery query)
    {
    	LogInResponse response = new LogInResponse();
    	
    	System.out.println("before authenticate");
        //execute a login
    	Authentication auth = Authentication.getInstance();
    	
    	System.out.println("after getInstance");
    	
    	int temp = auth.authenticate(query);
    	
    	System.out.println("after after setting SessionID = " + temp);
    	
    	response.sessionID = Integer.toString(temp);
    	
    	System.out.println("temp = " + temp);

    	if(temp != 0)
    		response.status = auth.getLevel(temp);
    	
    	System.out.println("after authenticate");
        
    	System.out.println("Status ==" + response.status);
    	
        if (response.status == 0) response.wasSuccessful = false;
        else if (response.status >= 1 && response.status <= 3)
        {
        	response.wasSuccessful = true;
        }
        else
        {
        	response.wasSuccessful = false;
            System.out.print("unexpected return value from authenticate...\n");
        }
        
        return response;
    }
    
    public static PasswordChangeResponse executePasswordChangeQuery(PasswordChangeQuery query)
    {
    	PasswordChangeResponse response = new PasswordChangeResponse();
    	
		//Check users authentication
    	int temp = Authentication.getInstance().authenticate(query);
    	response.sessionID = Integer.toString(temp);
		int status = Authentication.getInstance().getLevel(temp);
        
        if (status == 0) response.wasSuccessful = false;
        else if (status == 1 || status == 2 || status == 3)
        {
        	//execute a password change
        	int success = 0;
        	try {
    			success = Dbwrapper.getInstance().setNewPass(query.userName,query.oldPassword,query.newPassword);
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	
        	if(success == 1)
        	{
        		response.wasSuccessful = true;
        	}
        	else if(success == -1)
        	{
        		response.wasSuccessful = false;
        	}
        	else
        	{
        		response.wasSuccessful = false;
        		System.out.print("Unexpected return value from setNewPass\n");
        	}
        }
        else
        {
        	response.wasSuccessful = false;
            System.out.print("unexpected return value from authenticate...\n");
        }
    	

    	return response;
    }
    
    public static PayFinesResponse executePayFinesQuery(PayFinesQuery query)
    {
    	PayFinesResponse response = new PayFinesResponse();
    	
		//Check users authentication
    	int temp = Authentication.getInstance().authenticate(query);
    	response.sessionID = Integer.toString(temp);
		int status = Authentication.getInstance().getLevel(temp);
        
        if (status == 0 || status == 1) response.wasSuccessful = false;
        else if (status == 2 || status == 3)
        {
        	//execute a pay fine
        	try {
        		response.wasSuccessful = true;
    			Dbwrapper.getInstance().payBalance(query.userName,query.paymentAmount);
    			response.fines = ""+Dbwrapper.getInstance().getBalance(query.userName);
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }
        else
        {
        	response.wasSuccessful = false;
            System.out.print("unexpected return value from authenticate...\n");
        }
    	
    	
    	return response;
    }
    
    public static AddLibrarianResponse executeAddLibrarianQuery(AddLibrarianQuery query)
    {
    	AddLibrarianResponse response = new AddLibrarianResponse();
    	
    	//Check users authentication
    	int temp = Authentication.getInstance().authenticate(query);
    	response.sessionID = Integer.toString(temp);
		int status = Authentication.getInstance().getLevel(temp);
        
        if (status == 0 || status == 1 || status == 2) response.wasSuccessful = false;
        else if (status == 3)
        {
        	//execute add user (librarian)
        	try {
        		response.wasSuccessful = true;
    			Dbwrapper.getInstance().addUser(query.userName, query.password, query.email, query.fName, query.lName, query.enotify, 2);
    			response.fName = query.fName;
    			response.lName = query.lName;
    			response.userName = query.userName;
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }
        else
        {
        	response.wasSuccessful = false;
            System.out.print("unexpected return value from authenticate...\n");
        }
    	
    	
    	return response;
    }
    
    public static RemoveLibrarianResponse executeRemoveLibrarianQuery(RemoveLibrarianQuery query)
    {
    	RemoveLibrarianResponse response = new RemoveLibrarianResponse();
    	
    	//Check users authentication
    	int temp = Authentication.getInstance().authenticate(query);
    	response.sessionID = Integer.toString(temp);
		int status = Authentication.getInstance().getLevel(temp);
        
        if (status == 0 || status == 1 || status == 2) response.wasSuccessful = false;
        else if (status == 3)
        {
        	//Remove a librarian
//        	try {
//        		
//    		} catch (SQLException e) {
//    			// TODO Auto-generated catch block
//    			e.printStackTrace();
//    		}
        }
        else
        {
        	response.wasSuccessful = false;
            System.out.print("unexpected return value from authenticate...\n");
        }
    	
    	
    	return response;
    }
    
    public static SetFineResponse executeSetFineQuery(SetFineQuery query)
    {
    	SetFineResponse response = new SetFineResponse();
    	
    	//Check users authentication
    	int temp = Authentication.getInstance().authenticate(query);
    	response.sessionID = Integer.toString(temp);
		int status = Authentication.getInstance().getLevel(temp);
        
        if (status == 0 || status == 1 || status == 2) response.wasSuccessful = false;
        else if (status == 3)
        {
        	//Set the fine rate
//        	try {
//        		
//    		} catch (SQLException e) {
//    			// TODO Auto-generated catch block
//    			e.printStackTrace();
//    		}
        }
        else
        {
        	response.wasSuccessful = false;
            System.out.print("unexpected return value from authenticate...\n");
        }
    	
    	
    	return response;
    }
    
    public static Response executeQuery(Query query)
    {

        if(query instanceof BookInfoQuery)
        {
            System.out.println("Before bookinfo");
            BookInfoResponse response = executeBookInfoQuery((BookInfoQuery)query);
            System.out.println("after bookinfo");
            return response;
        }
        if(query instanceof CheckInBookQuery)
        {
            CheckInBookResponse response = executeCheckInBookQuery((CheckInBookQuery)query);
            return response;
        }
        if(query instanceof CheckOutBookQuery)
        {
            CheckOutBookResponse response = executeCheckOutBookQuery((CheckOutBookQuery)query);
            return response;
        }
        if(query instanceof LoginQuery)
        {
            System.out.println("Before executeLoginQuery");
            LogInResponse response = executeLogInQuery((LoginQuery)query);
            return response;
        }
        if(query instanceof AddBookQuery)
        {
            AddBookResponse response = new AddBookResponse();
            try 
            {
				response = executeAddBookQuery((AddBookQuery)query);
			} 
            catch (InvalidISBNException | SQLException e) 
            {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return response;
        }
        if(query instanceof PasswordChangeQuery)
        {
            PasswordChangeResponse response = executePasswordChangeQuery((PasswordChangeQuery)query);
            return response;
        }
        if(query instanceof PayFinesQuery)
        {
            PayFinesResponse response = executePayFinesQuery((PayFinesQuery)query);
            return response;
        }
        if(query instanceof AddLibrarianQuery)
        {
            AddLibrarianResponse response = executeAddLibrarianQuery((AddLibrarianQuery)query);
            return response;
        }
        if(query instanceof RemoveLibrarianQuery)
        {
            RemoveLibrarianResponse response = executeRemoveLibrarianQuery((RemoveLibrarianQuery)query);
            return response;
        }
        if(query instanceof SetFineQuery)
        {
            SetFineResponse response = executeSetFineQuery((SetFineQuery)query);
            return response;
        }
        
        return new Response(false,"0");
    }
}