package com.team1.authentication;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;

import com.team1.db.Dbwrapper;
import com.team1.formatting.AdminQuery;
import com.team1.formatting.CheckInBookQuery;
import com.team1.formatting.CheckOutBookQuery;
import com.team1.formatting.LibrarianInfoQuery;
import com.team1.formatting.LibrarianQuery;
import com.team1.formatting.LoginQuery;
import com.team1.formatting.PatronInfoQuery;
import com.team1.formatting.Query;

public class Authentication {

	public static String type;
	private static String password;
	private static String username;
	private static int userLevel;
	private static String stringID;
	private static int Id; 
	private static int Return;
	static int tempLevel;
	
	HashMap<Integer, Integer> map;
	
	//Constructor
	public Authentication(Query query) 
	{
		  map = new HashMap<>();
	}
	
	//Returns 0 if an ID is not found. Returns an ID if it is
	public int authenticate(Query query)
	{
		//Get the type of Query
		type = Query.queryType;
		//If the query is a Long in request
		if(query instanceof LoginQuery) 
		{
			Dbwrapper myDatabase = Dbwrapper.getInstance();
			//Get the username, password, and userlevel from the query object
			password = ((LoginQuery)query).password; //Modified to make it compile -Brandon
			username = ((LoginQuery)query).userName;
			//userLevel = 2;
			//Query the DB with the username and password
			try {
				System.out.println(username);
				userLevel = myDatabase.getAuthorization(username, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//0, 1, 2, or 3 is returned from the DB. 
			
			//0 means that the user was not found
				if(userLevel == 0)
				   {
				   		Return = 0;
				   		return Return;
				   }
		    //1 is a patron 2 is a librarian and 3 is an Admin. Store this info in the hashmap
				else if (userLevel == 1 || userLevel == 2 || userLevel == 3)
				   {
					  int LocalId = 0;
					  //Randomly generate the ID
                      Random n = new Random();
                      LocalId = n.nextInt(89999999) + 10000000;
                      System.out.println(LocalId + " " + userLevel);
                      //Store it in the hash map
                      map.put(LocalId, userLevel);
                      System.out.println(" Well: " + map.get(LocalId));
                      Return = LocalId;
                      return Return;
				   }
				   else
				   {
				   //Error handling
				   System.out.print("Something has gone terribly wrong with authentication");
				   return 0;
				   }				   
				 				
			}
		
		//If a patron (and only a patron) is requesting their information, verify their session
		else if(query instanceof PatronInfoQuery)
		{
			//Get the ID from the Librarian Query 
			stringID = ((LibrarianQuery)query).sessionID;
			Id = Integer.parseInt(stringID);
			//Check the hashmap for the Id. Get back their userLevel as an integer
			tempLevel = map.get(Id);
			//If the userLevel is exactly 1, the request has been verified
			if(tempLevel == 1)
			{
				return Id;
			}
			else
			{
		    return 0;
			}
		}

		//If an Admin is trying to do something verify their session
		else if(query instanceof AdminQuery)
		{
			stringID = ((LibrarianQuery)query).sessionID;
			Id = Integer.parseInt(stringID);
			tempLevel = map.get(Id);
			if(tempLevel > 2)
			{
				return Id;
			}
			else
			{
		    return 0;
			}
		}
		
		//If an Admin or a Librarian wants to check in a book
		else if(query instanceof CheckInBookQuery)
		{
			stringID = ((LibrarianQuery)query).sessionID;
			Id = Integer.parseInt(stringID);
			tempLevel = map.get(Id);
			if(tempLevel > 1)
			{
				return Id;
			}
			else
			{
		    return 0;
			}
		}
		
		//If an Admin or a Librarian wants to check out a book
		else if(query instanceof CheckOutBookQuery)
		{
			stringID = ((LibrarianQuery)query).sessionID;
			Id = Integer.parseInt(stringID);
			tempLevel = map.get(Id);
			if(tempLevel > 1)
			{
				return Id;
			}
			else
			{
		    return 0;
			}
		}
		
		//if a Librarian or Admin wants to look up the information of a Librarian
		else if(query instanceof LibrarianInfoQuery)
		{
			stringID = ((LibrarianQuery)query).sessionID;
			Id = Integer.parseInt(stringID);
			tempLevel = map.get(Id);
			if(tempLevel > 2)
			{
				return Id;
			}
			else
			{
		    return 0;
			}
		}
		
		//If a Librarian has a query or an Admin makes a Librarian level query
		else if(query instanceof LibrarianQuery)
		{
			stringID = ((LibrarianQuery)query).sessionID;
			Id = Integer.parseInt(stringID);
			System.out.println("The id is: " + Id);
			map.put(11111111, 2);
			tempLevel = map.get(Id);
			if(tempLevel > 1)
			{
				return Id;
			}
			else
			{
		    return 0;
			}
		}
		
		//error handling
		else{return 0;}
		}
	
/*		public static void main(String[] args)
		{
			LibrarianQuery dummy = new LibrarianQuery(true, "11111111");
			Authentication person = new Authentication(dummy);
			int temp88;
			temp88 = person.authenticate(dummy);
			System.out.println("temp 88 is: " + temp88);
			
		} */
}
