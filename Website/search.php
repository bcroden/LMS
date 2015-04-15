<html>
<head>
   <title>LMS</title>
<style>

	div.info{
		border-top: 1px solid black;
		padding: 10px;
		text-align: center;
	}

	div.result{
		background-color: #FFFFFF;
		border: 1px solid black;
		padding: 10px;
		width: 50%;
		margin-left: auto;
		margin-right: auto;
		border-radius: 10px;
	}

         body {
            background-color: #A3CCA3;
         }


//might make another div for clicking sub items

</style>

</head>
<body bgcolor="#A3CCA3">
      <?php include "log_button.php"; ?>
<?php
//error_reporting(E_ALL);
//ini_set('display_errors', 'On');
include 'bookSlider.php';
//include 'DBwrapper.php';

//echo "Connecting to database <br><br>";
connect();
	$type = $_POST['type'];

	if($type === "isbn"){
        $result = queryISBN($_POST['search']);
		if($result === -1){
            echo "Search returned no results...<br>";
        }
        else{
			echo "<div class=result>";
			echo "Searching by: " . $type . "<br>";
            while($row = mysqli_fetch_array($result)){
                //The return can be adjusted as we feel necessary
				echo "<div class=info>";
                echo "Title: " . $row["title"] . "<br>Author: " . $row["author"] .
                "<br>Copies available: " . $row["copiesin"] . "<br><br>";
				include 'image.php';
				echo "</div>";
            }
			echo "</div>";
		}
    }

	else if($type === "title"){
		$result = queryTitle($_POST['search']);
		if($result === -1){
            echo "Search returned no results...<br>";
        }
        else{
			echo "<div class=result>";
			echo "Searching by: " . $type . "<br>";
            while($row = mysqli_fetch_array($result)){
                //The return can be adjusted as we feel necessary
                echo "<div class=info>";
                echo "Title: " . $row["title"] . "<br>Author: " . $row["author"] .
                "<br>Copies available: " . $row["copiesin"] . "<br><br>";
                include 'image.php';
                echo "</div>";
            	}
            }
        }

	else if($type === "author"){
        $result = queryAuthor($_POST['search']);
		if($result === -1){
            echo "Search returned no results...<br>";
        }
        else{
			echo "<div class=result>";
			echo "Searching by: " . $type . "<br>";
            while($row = mysqli_fetch_array($result)){
                //The return can be adjusted as we feel necessary
                echo "<div class=info>";
                echo "Title: " . $row["title"] . "<br>Author: " . $row["author"] .
                "<br>Copies available: " . $row["copiesin"] . "<br><br>";
                include 'image.php';
                echo "</div>";
            }
            echo "</div>";
            }
		}

	else if($type === "genre"){
        $result = queryGenre($_POST['search']);
		if($result === -1){
			echo "Search returned no results...<br>";
		}
		else{
			echo "<div class=result>";
			echo "Searching by: " . $type . "<br>";
            while($row = mysqli_fetch_array($result)){
                //The return can be adjusted as we feel necessary
                echo "<div class=info>";
                echo "Title: " . $row["title"] . "<br>Author: " . $row["author"] .
                "<br>Copies available: " . $row["copiesin"] . "<br><br>";
                include 'image.php';
                echo "</div>";
            }
            echo "</div>";
        	}
		}
?>

</body>
</html>
