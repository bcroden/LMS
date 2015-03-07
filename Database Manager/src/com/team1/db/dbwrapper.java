package com.team1.db;

import java.sql.*;

public class dbwrapper {

	private static dbwrapper db = new dbwrapper();
	Connection con;
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql//localhost:3306/LMS";
	
	static final String username = "cadeg";
	static final String pass = "";
	
	//each function will concatenate sql queries
	//and return the result to the request
	//from authentication
	
	private dbwrapper(){
		//get driver and connection
		try{
			Class.forName(JDBC_DRIVER).newInstance();
			//connect
			con = DriverManager.getConnection(DB_URL, username, pass);
			System.out.println("Connected if we get here");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
		
		//instance method for singleton
		public static dbwrapper getInstance(){
			return db;
			
		}
		
	public ResultSet query() throws SQLException
	{
		Statement stmt = con.createStatement();
		String sql = "Select * From book";
		return stmt.executeQuery(sql);
		
	}
		
	public static void main(String args[]) throws SQLException
	{
		dbwrapper temp = getInstance();
		System.out.println(temp.query());
	}
}
	
