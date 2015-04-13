<?php
echo <<< EOT
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

	div.image{
		padding-top: 5px;
	}
</style>
EOT;

//	   error_reporting(E_ALL);
//	   ini_set('display_errors', 'On');


		include 'DBwrapper.php';
		connect();
		echo "<body>";
		echo "<div class=side>";
		echo "Our newest Selections: <br>";
		$result = top();
		$i = 0;
		while($row = mysqli_fetch_array($result)){
				echo "<form id=" . $i . " action=/bookInfo.php method=post>";
				//create a sub-div with book information
				echo "<input type=hidden name=isbn value=" . $row["isbn"] . ">";
				echo "<div class=book onclick=document.forms['" . $i . "'].submit();>";
				echo $row["title"] . "<br>";
				//Add the thumbnail display
				if(!ctype_space($row["picURL"])){
						echo "<div class=image>";
						include 'image.php';
						echo "</div>";
                    }
				//echo "URL: " . $row["picURL"] . "<br>";
				echo "</div>";
				echo "</form>";
				$i++;
		}
		echo "</div>";
		echo "</body>";
?>
