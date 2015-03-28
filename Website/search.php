<?php

error_reporting(E_ALL);
ini_set('display_errors', 'On');

include 'DBwrapper.php';

echo "Connecting to database <br>";
connect();
echo "Searching by title<br>";
	$result = queryTitle($_POST['search']);

	while($row = mysqli_fetch_array($result)){
		echo "id: " . $row["id"] . "<br>";
	}

?>

