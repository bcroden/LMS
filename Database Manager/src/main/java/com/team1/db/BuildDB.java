package com.team1.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
//get book objects


public class BuildDB {

	private static BuildDB db = new BuildDB();
	Connection con;
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/LMS";
	//Can adjust these and they don't have to be hard coded
	static final String username = "root";
	static final String pass = "LMS";
	
	//each function will concatenate sql queries
	//and return the result to the request
	//from authentication
	
	
	private BuildDB(){
		//get driver and connection
		try{
		//System.out.println("Dbwrapper");
			Class.forName(JDBC_DRIVER).newInstance();
			//connect
			con = DriverManager.getConnection("jdbc:mysql://localhost/", "root", "LMS");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
		
		//instance method for singleton
		public static BuildDB getInstance(){
			return db;
			
		}
		
		public void createDB() throws SQLException{
			Statement stmt = con.createStatement();
			String sql = "CREATE DATABASE IF NOT EXISTS LMS";
			stmt.executeUpdate(sql);
		}
		
		public void addTables() throws SQLException{
			Statement stmt = con.createStatement();
			String sql = "USE LMS";
			stmt.executeQuery(sql);
			sql = "CREATE TABLE IF NOT EXISTS book "
						+ "(id INT NOT NULL AUTO_INCREMENT, isbn VARCHAR(13) UNIQUE, "
						+ "title VARCHAR(100), author VARCHAR(100), genre VARCHAR(50), "
						+ "likes INT, dislikes INT, publisher VARCHAR(100), publishdate YEAR, "
						+ "copiesin INT, copiesout INT, copiesreserved INT, usersreserved VARCHAR(1500)"
						+ ", picURL VARCHAR(300), bookloc VARCHAR(15), PRIMARY KEY(id))";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE IF NOT EXISTS user "
						+ "(id INT NOT NULL AUTO_INCREMENT, username VARCHAR(100) UNIQUE, "
						+ "password VARCHAR(100), fname VARCHAR(100), lname VARCHAR(100), "
						+ "email VARCHAR(100), balance INT, notify INT, booksout VARCHAR(1000), "
						+ "dateout VARCHAR(1000), history VARCHAR(3000), auth INT, booksr VARCHAR(1000), PRIMARY KEY(id))";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE IF NOT EXISTS settings "
					+ "(rate FLOAT NOT NULL, days INT NOT NULL)";
			stmt.executeUpdate(sql);
		}

		public static void main(String args[]) throws SQLException
		{
			BuildDB temp = getInstance();
			temp.createDB();
			temp.addTables();
		}
		
}