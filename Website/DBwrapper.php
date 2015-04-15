<?php

//error_reporting(E_ALL);
//ini_set('display_errors', 'On');

	if(!isset($INCLUDED)){
		$INCLUDED = true;
	$host = "localhost";
	$user = "root";
	$pass = "LMS";
	$db = "LMS";
	$port = "3306";
	$connection;

	function connect(){
		global $host, $user, $pass, $db, $port, $connection;
		$connection = mysqli_connect($host, $user, $pass, $db, $port)or die("Error: " . $connection->connect_error);
		//echo "<br>Connected to database<br>"; //debug line
	}

	function getUsers(){
		global $connection;
		$sql = "SELECT username FROM user";
		$result = $connection->query($sql);
		if($result->num_rows > 0){
			return $result;
		}
		else
			return -1;
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

	function setNewPass($user, $oldPass, $newPass){
		global $connection;
		$sql = "SELECT password FROM user WHERE username = '" . $user . "'";
		$result = $connection->query($sql);
		$row = mysqli_fetch_array($result);
		if($oldPass == $row["password"]){
			$sql = "UPDATE user SET password = '" . $newPass . "' WHERE username = '" . $user . "'";
                        $connection->query($sql);
		}
	}

	function setNotification($user, $notify){
		global $connection;
          	$sql = "UPDATE user SET notify = '" . $notify . "' WHERE username = '" . $user . "'";
                $connection->query($sql);
	}


	function getBooksOut($user){
		global $connection;
		$sql = "SELECT booksout FROM user WHERE username = '" . $user . "'";
		$result = $connection->query($sql);
		if($result->num_rows > 0){
			return $result;
		}
		else
			return -1;
	}

	function calcTime($user, $isbn){
		global $connection;
		$sql = "SELECT booksout FROM user WHERE username = '" + $user . "'";
		$result = $connection->query($sql);
		if($result->num_rows < 0){
			//no times
		}
		else{
        $row = mysqli_fetch_array($result);
		$books = explode(",", $row["booksout"]);

		$i = 0;
		for($i; $i < count($books); $i++){
			if($books[$i] === $isbn) break;
		}

		$sql = "SELECT dateout FROM user WHERE username = '" . $user . "'";
		$result = $connection->query($sql);
		$row = mysqli_fetch_array($result);

		$times = explode(",", $row["dateout"]);
		$time = (time() - $times[$i])/86400000;

		$timeleft = 90 - $time;

		return $timeleft;
		}
	}

	function changeEmail($user, $email){
		global $connection;
		$sql = "UPDATE user SET email = '" . $email . "' WHERE username = '" . $user . "'";
		$connection->query($sql);
	}

	function reserve($user, $isbn){
		global $connection;
		echo "ISBN: " . $isbn . "<br>";
		//find the values of the copies of books available
		$sql = "SELECT copiesin, copiesout, copiesreserved FROM book WHERE isbn = '" . $isbn . "'";
		$result = $connection->query($sql);
		$copiesin = 0;
		$copiesout = 0;
		$copiesr = 0;
		while($row = mysqli_fetch_array($result)){
			$copiesin = $row["copiesin"];
			$copiesout = $row["copiesout"];
			$copiesr = $row["copiesreserved"];
			echo "Copiesin: " . $row["copiesin"] . "<br>";
			echo "Direct: " . $row["copiesreserved"] . "<br>";
		}
			//add a reserved copy
			echo "reserved b: " . $copiesr . "<br>";
			$copiesr++;
			echo "reserved a: " . $copiesr . "<br>";

			$sql = "UPDATE book SET copiesreserved = " . $copiesr . " WHERE isbn = '" . $isbn . "'";
			$connection->query($sql);
			//add book to users reserved list
			$sql = "SELECT booksr FROM user WHERE username = '" . $user . "'";
			$result = $connection->query($sql);
			$books = "";
			while($row = mysqli_fetch_array($result)){
				$books = $row["booksr"];
			}
			$temp = $books . $isbn . ",";
			$books = $temp;
			$sql = "UPDATE user SET booksr = '" . $books . "' WHERE username = '" . $user . "'";
			$connection->query($sql);
			//Attach user to reservation list
			$sql = "SELECT userreserved FROM book WHERE isbn = '" . $isbn . "'";
			$result = $connection->query($sql);
			$users = "";
			while($row = mysqli_fetch_array($result)){
				$users = $row["userreserved"];
			}
			$userFinal = $users . "," . $user;
			$sql = "UPDATE book SET userreserved = '" . $userFinal . "'";
			$connection->query($sql);
	}

	function getReserved($user){
                global $connection;
		$sql = "SELECT booksr FROM user WHERE username = '" . $user . "'";
		$result = $connection->query($sql);

		return $result;
	}

	function top($amount){
		global $connection;

		$sql = "SELECT * FROM book ORDER BY id DESC LIMIT " . $amount;
		$result = $connection->query($sql);
		return $result;
	}

}
?>
