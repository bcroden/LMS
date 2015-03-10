package com.team1.authentication;

import com.team1.formatting.LoginQuery;
import com.team1.formatting.Query;

public class Authentication {

	public static String queryType;
	public static String type;
	private static boolean permission;
	private static String password;
	private static String username;
	private static String userLevel;
	private static int Id; 
	
	//Constructor
	public Authentication(Query query) 
	{
		// TODO
	}
	
	public static boolean authenticate(Query query)
	{
		type = query.queryType;
		if(type == "LoginQuery") //Consider using query instanceof LoginQuery -Brandon
		{
			password = ((LoginQuery)query).password; //Modified to make it compile -Brandon
			username = ((LoginQuery)query).userName;
			// Id = however I am getting the ID
			Id = 12345678;
			if(searchMap(Id) == true)
			{
				permission = true;
				return permission;
			}
			else
			{
				//userLevel = Database(username, password)
				//TODO check if(Type <= userLevel in terms of access granted
				/* if(true)
				   {
                      //randomly generate 8 digit number
                      //append 0,1,2 in correspondance with userLevel
                      //store in hashMap using addMap				 
				   }
				 
				 */
				permission = true;
				return permission;
			}
		}
//		else{return false;}
		
		else if(type == "PatronInfoQuery")
		{
			//TODO if ID is found, return true
		    return false;
		}
		else{return false;}
	
		//TODO AdminQuery
		//TODO CheckInBookQuery
		//TODO CheckOutBookQuery
		//TODO LibrarianInfoQuery
		//TODO LibrarianQuery
	}
	
	public static void addMap()
	{
		//TODO make hash map and add stuff
	}
	
	public static boolean searchMap(int ID)
	{
		//TODO hash map search
		return true;
	}
}
