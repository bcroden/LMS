/*package com.team1.authentication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.sql.SQLException;

import org.junit.Test;

import com.team1.db.Dbwrapper;
import com.team1.formatting.queries.AdminQuery;
import com.team1.formatting.queries.LoginQuery;

public class AdminQueryAuthenticateTEST {
	@Test(expected = SQLException.class)
	public void test() throws SQLException, InterruptedException{
		Dbwrapper myDatabase = Dbwrapper.getInstance();
		myDatabase.addUser("johny2", "words", "email", "firstname", "lastname", 1, 3);
		
		Thread.sleep(500);
		Authentication person = Authentication.getInstance();
		LoginQuery login = new LoginQuery("johny2", "words");
		int var = person.authenticate(login);
		assertEquals(3, var);
	}

}
*/
