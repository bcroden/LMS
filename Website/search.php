<!DOCTYPE html>
<html>
<head>
   <title>LMS</title>
<style>

	div.info{
		position: relative;
		border-top: 1px solid black;
		padding: 10px;
		width: 90%;
		text-align: center;
		margin-top: 0px;
		margin: auto;
		cursor:pointer;
	}

	div.result{
		background-color: #FFFFFF;
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
<?php
include 'navi.php';
include 'log_button.php';
error_reporting(E_ALL);
ini_set('display_errors', 'On');
include 'bookSlider.php';
//include 'DBwrapper.php';
connect();

	$type = $_GET['type'];

	if($type === "isbn"){
        $result = queryISBN($_GET['search']);
		if($result === -1){
            echo "Search returned no results...<br>";
        }
        else{
			echo "<div class=result>";
			echo "Searching by: " . $type . "<br>";
			$j = 400;
            while($row = mysqli_fetch_array($result)){
echo <<< EOT
<form id=$j  action=bookInfo.php method=post>
	<input type=hidden name=isbn value=$row[isbn]>
		<div class=info onclick=document.getElementById($j).submit();>
        Title: $row[title]<br>
		Author: $row[author]<br>
        Copies available: $row[copiesin] <br><br>
EOT;
					if(!ctype_space($row['picURL'])){
						include 'image.php';
					}
				echo "</div>";
				echo "</form>";
				$j++;
            }
			echo "</div>";
		}
    }

	elseif($type === "title"){
		$result = queryTitle($_GET['search']);
		if($result === -1){
            echo "Search returned no results...<br>";
        }
        else{
			echo "<div class=result>";
            echo "Searching by: " . $type . "<br>";
            $j = 100;
            while($row = mysqli_fetch_array($result)){
echo <<< EOT
<form id=$j  action=bookInfo.php method=post>
    <input type=hidden name=isbn value=$row[isbn]>
        <div class=info onclick=document.getElementById($j).submit();>
        Title: $row[title]<br>
        Author: $row[author]<br>
        Copies available: $row[copiesin] <br><br>
EOT;
                    if(!ctype_space($row['picURL'])){
                        include 'image.php';
                    }
                echo "</div>";
                echo "</form>";
                $j++;
            }
            echo "</div>";
        }
	}

	elseif($type === "author"){
        $result = queryAuthor($_GET['search']);
		if($result === -1){
            echo "Search returned no results...<br>";
        }
        else{
			echo "<div class=result>";
            echo "Searching by: " . $type . "<br>";
            $j = 100;
            while($row = mysqli_fetch_array($result)){
echo <<< EOT
<form id=$j  action=bookInfo.php method=post>
    	<input type=hidden name=isbn value=$row[isbn]>
        <div class=info onclick=document.getElementById($j).submit();>
        Title: $row[title]<br>
        Author: $row[author]<br>
        Copies available: $row[copiesin] <br><br>
EOT;
                    if(!ctype_space($row['picURL'])){
                        include 'image.php';
                    }
                echo "</div>";
                echo "</form>";
                $j++;
            }
            echo "</div>";
        }
	}

	elseif($type === "genre"){
        $result = queryGenre($_GET['search']);
		if($result === -1){
			echo "Search returned no results...<br>";
		}
		else{
			echo "<div class=result>";
            echo "Searching by: " . $type . "<br>";
            $j = 100;
            while($row = mysqli_fetch_array($result)){
echo <<< EOT
<form id=$j  action=bookInfo.php method=post>
    <input type=hidden name=isbn value=$row[isbn]>
        <div class=info onclick=document.getElementById($j).submit();>
        Title: $row[title]<br>
        Author: $row[author]<br>
        Copies available: $row[copiesin] <br><br>
EOT;
                    if(!ctype_space($row['picURL'])){
                        include 'image.php';
                    }
                echo "</div>";
                echo "</form>";
                $j++;
            }
            echo "</div>";
        }
	}
?>

</body>
</html>
