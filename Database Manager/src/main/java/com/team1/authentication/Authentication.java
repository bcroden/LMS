package com.team1.authentication;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import com.team1.db.Dbwrapper;
import com.team1.formatting.queries.*;

public class Authentication {

	public static String type;
	private static String password;
	private static String username;
	private static int userLevel;
	private static String stringID;
	private static int Id; 
	private static int Return;
	static int tempLevel;
	static int isTime;
	static long tempTime;
	
	HashMap<Integer, Integer> map;
	HashMap<Integer, Long> map2;
	
	//Constructor
	public Authentication(Query query) 
	{
		  map = new HashMap<>();
		  map2 = new HashMap<>();
	}
	
	public int timeout(long time){
		Date date = new Date();
		long temptime = date.getTime();
		if((temptime - time) > 21600000){
			return 0;
		}
		else{return 1;}		
	}
	
	public static Authentication getInstance() {
		return null;//TODO: do this -Brandon
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
                      //Print testing the input to the map
                      //System.out.println("ID and user level stored is" + LocalId + " " + userLevel);
                      //Store ID and userlevel in the hash map
                      map.put(LocalId, userLevel);
                      Date date = new Date();
                      long time = date.getTime();               
                      //Store the ID and the session time in hasmap2
                      map2.put(LocalId, time);
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
		else if(query instanceof AdminQuery)
		{
			//Get the ID from the Librarian Query 
			stringID = ((AdminQuery)query).sessionID;
			Id = Integer.parseInt(stringID);
			Date date = new Date();
			//Check the hashmap for the Id. Get back their userLevel as an integer
			tempLevel = map.get(Id);
			tempTime = map2.get(Id);
			isTime = timeout(tempTime);
			//If the userLevel is exactly 3, the request has been verified
			if((tempLevel == 3) && (isTime == 1))
			{
				map2.put(Id, date.getTime());
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
			// For testing purposes -> map.put(11111111, 2);
			Date date = new Date();
			
			tempLevel = map.get(Id);
			tempTime = map2.get(Id);
			//isTime is 1 if valid, 0 if invalid
			isTime = timeout(tempTime);
			if((tempLevel > 1) && (isTime == 1))
			{	
				//If valid update the session time
				map2.put(Id, date.getTime());
				return Id;
			}
			else
			{
		    return 0;
			}
		}
		
		else if(query instanceof Query)
		{
			//Get the ID from the Librarian Query 
			stringID = ((Query)query).sessionID;
			Id = Integer.parseInt(stringID);
			Date date = new Date();
			//Check the hashmap for the Id. Get back their userLevel as an integer
			tempLevel = map.get(Id);
			tempTime = map2.get(Id);
			isTime = timeout(tempTime);
			//If the userLevel is exactly 3, the request has been verified
			if((tempLevel > 0) && (isTime == 1))
			{
				map2.put(Id, date.getTime());
				return Id;
			}
			else
			{
		    return 0;
			}
		}
		
		//error handling
		else{System.out.println("There was an error in Authentication. No case was found");
			return 0;}
		}
	
	/*	public static void main(String[] args)
		{
			
			//Authentication person = new Authentication(dummy);
			int temp88;
			LoginQuery login = new LoginQuery("you", "this");
			Authentication person = new Authentication(login);
			//LibrarianQuery dummy = new LibrarianQuery("11111111");
			
			LibrarianQuery dummy = new LibrarianQuery("11111111");
			Authentication person2 = new Authentication(dummy);
			int temp99 = person2.authenticate(dummy);
			temp88 = person.authenticate(login);
			System.out.println("Log in ID is: " + temp88);
			System.out.println("Librarian Query ID is: " + temp99);
		} */
}
