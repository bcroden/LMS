<?php
//   error_reporting(E_ALL);
//   ini_set('display_errors', 'On');
//   if(isset($_SESSION["init"]))
      session_start();

   include 'bookSlider.php';

   if(isset($_POST["resBk"])) {
      if(!isset($_SESSION["init"])) {
         requestLogin();
      } else {
         reserve($_SESSION["usernm"], $_POST["isbn"]);
      }
   } else {

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
<!DOCTYPE html>
<html>
   <head>
      <title>LMS</title>
   </head>
   <body>
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
				//TODO: Print the thumbnail


            ?>
         </p>
      </div>
      <div id=Btn class=reserve>
         <form action="bookInfo.php" method="post">
            <input type="submit" value="Reserve Book"/>
            <input type="hidden" name="isbn" value="<?= $isbn ?>"/>
            <input type="hidden" name="resBk" value="yes"/>
         </form>
      </div>
      <?php include "log_button.php"; ?>
   </body>
</html>
<?php
      }  //end of displayInfo()
   }

   function requestLogin() {
?>
<!DOCTYPE html>
<html>
   <head>
      <title>LMS</title>
   </head>
   <body>
      <h1>Wait!</h1>
      <p>You must login to reserve a book.</p>
      <form action="accounts/login.php" method="post">
         <input type="submit" value="Login"/>
         <input type="hidden" name="isbn" value="<?= $_POST['isbn'] ?>"/>
         <input type="hidden" name="return" value="/bookInfo.php"/>
      </form>      
   </body>
</html>
<?php
   } // end of requestLogin()
?>
