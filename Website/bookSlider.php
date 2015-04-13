<html>
<style>

	div.side{
	 	background:lightblue;
		position: fixed;
		border: solid 1px black;
		right:5px;
		top: 45px;
		width:200px;
		padding: 10px;
		border-radius: 10px;
	}

	div.book{
		border-top: solid 1px black;
		padding-top: 5px;
		padding-bottom: 5px;
	}

</style>

	<?php
		include 'DBwrapper.php';
		connect();
		echo "<div class=side>";
		echo "Our newest Selections: <br>";
			$result = top();

		while($row = mysqli_fetch_array($result)){
			//create a sub-div with book information
			echo "<div class=book>";
			echo "Title: " . $row["title"] . "<br>";
		//	if(($row["picURL"] === " ");
		//	else{
		//		header('Content-type: image/jpeg');
		//		echo file_get_contents($row["picURL"]);
		//	}
			echo "</div>";
		}
		echo "</div>";
	?>
</html>

