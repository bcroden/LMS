<?php

	$host = "localhost";
	$user = "root";
	$pass = "LMS";

    $connection = mysqli_connect($host, $user, $pass);
	if(!$connection){
		echo "Error connecting, yo";
	}
	else{
	//form the sql queries to create the database and tables
	$sql = "CREATE DATABASE IF NOT EXISTS LMS";
	$connection->query($sql);

	$sql = "USE LMS";
	$connection->query($sql);
	//create the tables 
	$sql = "CREATE TABLE IF NOT EXISTS book (id INT NOT NULL AUTO_INCREMENT, isbn VARCHAR(13) UNIQUE, title VARCHAR(100), author VARCHAR(100), genre VARCHAR(50), publisher VARCHAR(100), publishdate YEAR, likes INT, dislikes INT, copiesin INT, copiesout INT, PRIMARY KEY(id))";
	$connection->query($sql);
	$sql = "CREATE TABLE IF NOT EXSISTS user (id INT NOT NULL AUTO_INCREMENT, username VARCHAR(100) UNIQUE, password VARCHAR(100), fname VARCHAR(100), lname VARCHAR(100), email VARCHAR(100), balance INT, notify INT, booksout VARCHAR(1000), history VARCHAR(3000), auth INT, PRIMARY KEY(id))";
	$connection->query($sql); 
	}

	echo "Database created";
?>
