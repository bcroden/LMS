<html>
<head>
   <title>LMS</title>
<style>
div.scroll {
	background-color: D6D6D6;
    height: 200px;
    overflow: auto;
}

//might make another div for clicking sub items

</style>

</head>
<body>
      <?php include "log_button.php"; ?>
<?php
error_reporting(E_ALL);
ini_set('display_errors', 'On');
include 'bookSlider.php';
//include 'DBwrapper.php';

//echo "Connecting to database <br><br>";
connect();
	$type = $_POST['type'];

	echo "Searching by: " . $type . "<br>";

	if($type === "isbn"){
        $result = queryISBN($_POST['search']);
        echo "<div class=scroll>";
		if($result === -1){
            echo "Search returned no results...<br>";
        }
        else{
            while($row = mysqli_fetch_array($result)){
                //The return can be adjusted as we feel necessary
                echo "Title: " . $row["title"] . "<br>Author: " . $row["author"] .
                "<br>Copies available: " . $row["copiesin"] . "<br><br>";
            }
		}
        echo "</div>";
    }

	else if($type === "title"){
		$result = queryTitle($_POST['search']);
		echo "<div class=scroll>";
		if($result === -1){
            echo "Search returned no results...<br>";
        }
        else{
            while($row = mysqli_fetch_array($result)){
                //The return can be adjusted as we feel necessary
                echo "Title: " . $row["title"] . "<br>Author: " . $row["author"] .
                "<br>Copies available: " . $row["copiesin"] . "<br><br>";
            }
        }
			//I want to make each item clickable to view more information
			//on that specific book but it is looking very complex to do
			//with the list in this format
			//I'll look into changing it but adding a link at the bottom
			//of each item would solve it too
		echo "</div>";
	}

	else if($type === "author"){
        $result = queryAuthor($_POST['search']);
        echo "<div class=scroll>";
		if($result === -1){
            echo "Search returned no results...<br>";
        }
        else{
            while($row = mysqli_fetch_array($result)){
                //The return can be adjusted as we feel necessary
                echo "Title: " . $row["title"] . "<br>Author: " . $row["author"] .
                "<br>Copies available: " . $row["copiesin"] . "<br><br>";
            }
		}
        echo "</div>";
    }

	else if($type === "genre"){
        $result = queryGenre($_POST['search']);
        echo "<div class=scroll>";
		if($result === -1){
			echo "Search returned no results...<br>";
		}
		else{
        	while($row = mysqli_fetch_array($result)){
            	//The return can be adjusted as we feel necessary
            	echo "Title: " . $row["title"] . "<br>Author: " . $row["author"] .
            	"<br>Copies available: " . $row["copiesin"] . "<br><br>";
        	}
		}
        echo "</div>";
    }
?>

</body>
</html>
