package com.team1.authentication;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;

import com.team1.db.Dbwrapper;
import com.team1.formatting.LibrarianQuery;
import com.team1.formatting.LoginQuery;
import com.team1.formatting.Query;

public class Authentication {

	public static String queryType;
	public static String type;
	private static boolean permission;
	private static String password;
	private static String username;
	private static int userLevel;
	private static int Id; 
	private static int Return;
	static String temp77;
	
	//Constructor
	public Authentication(Query query) 
	{
		// TODO
	}
	
	//Returns 0 if an ID is not found. Returns an ID if it is
	public int authenticate(Query query)
	{
		 HashMap<Integer, Integer> map = new HashMap<>();
		
		type = Query.queryType;
		if(query instanceof LoginQuery) 
		{
			//Dbwrapper myDatabase = Dbwrapper.getInstance();
			password = ((LoginQuery)query).password; //Modified to make it compile -Brandon
			username = ((LoginQuery)query).userName;
			userLevel = 2;
		/*	try {
				System.out.println(username);
				//userLevel = myDatabase.getAuthorization(username, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		*/	
				if(userLevel == 0)
				   {
				   		Return = 0;
				   		return Return;
				   }
				
				else if (userLevel == 1 || userLevel == 2 || userLevel == 3)
				   {
					  int LocalId = 0;
					//  int temp99 = map.get(62045572);
					 // System.out.println("The key should be: " + temp99);
                      Random n = new Random();
                      LocalId = n.nextInt(89999999) + 10000000;
      //I comented out this line the 2nd time I ran the code and it gave me a null pointer exception
                      map.put(11111111, userLevel);
                      int temp99 = map.get(11111111);
                      System.out.println("this is: temp99: " + LocalId);
                      Return = LocalId;
                      return Return;
				   }
				   else
				   {
				   System.out.print("Something has gone terribly wrong with authentication");
				   return 0;
				   }				   
				 				
			}
		
		
		else if(type == "PatronInfoQuery")
		{
			//Use serachMap(username, ID)
			//Get first digit and compare with user level
			//if firstDigit <= userlevel return true
		    return 0;
		}
		else{return 0;}
		}
	
		//TODO AdminQuery
		//TODO CheckInBookQuery
		//TODO CheckOutBookQuery
		//TODO LibrarianInfoQuery
		//TODO LibrarianQuery
		
	
	

		public static void main(String[] args)
		{
			LoginQuery dummy = new LoginQuery("11111111", "bob", "pass");
			System.out.println("Hello there");
			Authentication person = new Authentication(dummy);
			int temp88;
			temp88 = person.authenticate(dummy);
			System.out.println("temp 88 is: " + temp88);
			
		}
}
