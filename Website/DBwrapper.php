<?php

	error_reporting(E_ALL);
	ini_set('display_errors', 'On');

	$host = "localhost";
	$user = "root";
	$pass = "LMS";
	$db = "LMS";
	$port = "3306";
	$connection;

	function connect(){
		global $host, $user, $pass, $db, $port, $connection;
		$connection = mysqli_connect($host, $user, $pass, $db, $port)or die("Error: " . $connection->connect_error);
		echo "<br>Connected to database<br>"; //debug line
	}
	//return multiple books
	function queryTitle($title){
		global $connection;

		$sql = "SELECT * FROM book WHERE title = '" . $title . "'";
		$result = $connection->query($sql);
		//check to see if empty
		if($result->num_rows > 0){
			return $result;
			//The result object deal should have all the entries related
			//this can be retrieved with a while loop
			//while($row = mysqli_fetch_array($result)){//do stuff}
		}
		else{
			//return a value showing that the query is empty
			return -1;
		}
	}

	function queryAuthor($author){
		global $connection;
		$sql = "SELECT * FROM book WHERE author = '" . $author . "'";
		$result  = $connection->query($sql);
		if($result->num_rows > 0){
			return $result;
		}
		else
			return -1;
	}

	function queryGenre($genre){
		global $connection;
		$sql = "SELECT * FROM book WHERE genre '" . $genre . "'";
		$result = $connection->query($sql);
		if($result->num_rows > 0){
			return $result;
		}
		else
			return -1;
	}

	//returns singular result
	function queryISBN($isbn){
		global $connection;
		$sql = "SELECT * FROM book WHERE isbn = '" . $isbn . "'";
		$result = $connection->query($sql);
		if($result->num_rows > 0){
			return $result;
		}
		else
			return -1;
	}

	//user related requests
	function addUser($user, $pass, $email, $fname, $lname, $notify, $auth){
		global $connection;
		$sql = "INSERT INTO user (username, password, fname, lname, " .
			   "email, notify, auth) VALUES ('" . $user . 
			   "', '" . $pass . "', '" . $fname . "', '" . $lname . 
               "', '" . $email . "', '" . $notify . "', '" . $auth . "')";
		$result = $connection->query($sql);
		//if($result->num_rows > 0){
		//	return $result;
		//}
		//else
		//	return -1;
	}

	function getUserInfo($user){
		global $connection;
		$sql = "SELECT * FROM user WHERE username = '" . $user . "'";
		$result = $connection->query($sql);
        if($result->num_rows > 0){
          return $result;
        }
        else
          return -1;

	}

	function getPass($user){
		global $connection;
		$sql = "SELECT password FROM user WHERE username = '" . $user . "'";
		$result = $connection->query($sql);
		if($result->num_rows > 0){
			return $result;
		}
		else{
			return -1;
		}
	}
?>

