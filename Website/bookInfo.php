<!DOCTYPE html>
<html>
<?php
   session_start();
   include 'bookSlider.php';
   include 'navi.php';
   include 'log_button.php';
   $widthpx = $width . "px";
//setting up style
echo <<< EOT
	<style>
		div.info{
			background-color: #FFFFFF;
			position: relative;
			border: solid 1px black;
			border-radius: 10px;
			padding: 5px;
			width: 50%;
			margin-left: auto;
			margin-right: auto;
		}

		div.pic{
			position: relative;
			border: 1px solid black;
			border-radius: 10px;
			padding-left: 5px;
			padding-right: 5px;
			padding-top: 10px;
			padding-bottom: 5px;
			width: $widthpx;
			margin-left: auto;
			margin-right: auto;
			background-color: #FFFFFF;
			text-align: center;
		}

		div.pic.btn{
			posisiton: relative;
			width: 10%;
			margin-left: auto;
			margin-right: auto;
		}

	</style>
EOT;

   if(isset($_POST["resBk"])) {
      //reserve request was submitted
      if(!isset($_SESSION["init"])) {
         //user is not logged in
         requestLogin();
      } else {
         //reserve book assumes reservation is successful
         reserve($_SESSION["usernm"], $_POST["isbn"]);
         success();
      }
   } else
   {
      //get book information using provided ISBN
      $isbn = "";
      if(isset($_POST["isbn"]))
         $isbn = $_POST["isbn"];
      if(isset($_SESSION["isbn"]))
         $isbn = $_SESSION["isbn"];

      $result = queryISBN($isbn);
      if($result === -1){
         echo "Error: no information on book. <br> " .
         "Please contact the Library Administrator<br>";
      }
      else{
         $row = $result->fetch_assoc();
?>
   <head>
      <title>LMS</title>
   </head>
   <body bgcolor="#A3CCA3">
      <div class=info>
         <p><?php
            echo "ISBN: " . $row["isbn"] . "<br>";
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
					echo "Available Copies: 0<br/>";
				}
				else{
					echo "Available Copies: " . $copiesAvail . "<br>";
				}
				echo "Location in-house: " . $row["bookloc"] . "<br>";
				echo "</div>";
            ?>
         </p>
	<div class=pic>
		<?php
			include 'image.php';
		?>
      <div class=btn>
         <form action="bookInfo.php" method="post">
            <input type="submit" value="Reserve Book"/>
            <input type="hidden" name="isbn" value="<?= $isbn ?>"/>
            <input type="hidden" name="resBk" value="yes"/>
         </form>
      </div>
	</div>
   </body>
<?php
      }  //end of displayInfo()
   }

   function requestLogin() {
?>
   <head>
      <title>LMS</title>
      <style>
         body {
            background-color: A3CCA3;
         }

         #centered {
            text-align: center;
            margin: auto;
         }
      </style>
   </head>
   <body>
      <div id="centered">
         <h1>Wait!</h1>
         <p>You must login to reserve a book.</p>
         <form action="login.php" method="post">
            <input type="submit" value="Login"/>
            <input type="hidden" name="isbn" value="<?= $_POST['isbn'] ?>"/>
            <input type="hidden" name="return" value="/bookInfo.php"/>
         </form>
      </div>
   </body>
<?php
   } // end of requestLogin()

   function success() {
?>
   <head>
      <title>LMS</title>
      <style>
         body {
            background-color: #A3CCA3;
         }
		div.success{
			width: 50%;
			margin-left: auto;
			margin-right: auto;
			background-color: #FFFFFF;
			border-radius: 10px;
			padding: 10px;
			text-align: center;
		}
      </style>
   </head>
   <body>
		<div class="success">
      		<p>Book Reserved Successfully</p>
		</div>
   </body>
<?php
   }
?>
</html>
