<?php
	if(!isset($_SESSION["init"])){
		echo "You are not logged in<br>";
		echo "Recieved: " . $_POST["isbn"];
	}
	else{
		echo "You have logged in<br>";
	}

	include 'bookSLider.php';

	$isbn = $_POST["isbn"];
	$result = queryISBN($isbn);

	if($result === -1){
		echo "Error: no information on book. <br> " .
		"Please contact the Library Administrator<br>";
	}
	else{
		while($row = mysql_fetch_array($result)){
			echo "<div class=info>";
				echo "Title: " . $row["title"] . "<br>";
				echo "Author: " . $row["author"] . "<br>";
				echo "Genre: " . $row["genre"] . "<br>";
				echo "Publisher: " . $row["publisher"] . "<br>";
				echo "Publishing Date: " . $row["publishdate"] . "<br>";
				echo "<div class=like>";
					echo "Likes: " . $row["likes"] . "<br>";
					echo "Dislikes: " . $row["dislikes"] . "<br>";
				echo "</div>";
				$copiesAvail = $row["copiesin"] - $row["copiesreserved"];
				if($copiesAvail < 0){
					echo "Available Copies: 0";
				}
				else{
					echo "Available Copies: " . $copiesAvail . "<br>";
				}
				echo "Location in-house: " . $row["bookloc"] . "<br>";
				//Print the thumbnail



				//reserve a copy
				echo"<div id=Btn class=reserve><button id=ReserveButton onclick=";
				reserve($_SESSION["user"], $_SESSION["isbn"]);
				echo ">Reserve</button></div>";
		}
	}

?>
