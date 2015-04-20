<!DOCTYPE html>
<?php
   session_start();

   if($_POST["form_type"] === "update") {
      include "bookSlider.php";

      $good_pass = true;

      if($_POST["email"] != $_SESSION["email"]) {
         changeEmail($_SESSION["usernm"], $_POST["email"]);
        $_SESSION["email"] = $_POST["email"];
      }
      if($_POST["password"] != $_SESSION["pass"] or $_POST["password2"] != $_SESSION["pass"]) {
         if($_POST["password"] === $_POST["password2"]) {
            setNewPass($_SESSION["usernm"], $_SESSION["pass"], $_POST["password"]);
            $_SESSION["pass"] = $_POST["new_password"];
         }
         else
            $good_pass = false;
      }
      if(isset($_POST["notify"])) {
         setNotification($_SESSION["usernm"], $_POST["notify"]);
         $_SESSION["notify"] = $_POST["notify"];
      }

      if($good_pass)
         header("Location: view_account.php");
   }

	include 'bookSlider.php';
	include 'navi.php';
?>

<html>

   <head>
      <title>LMS</title>
      <style>

	div.centered{
		position: relative;
		border: solid 1px black;
		border-radius: 10px;
		padding: 10px;
		width: 50%;
		top: 45px;
		margin-left: auto;
		margin-right: auto;
        background-color: #FFFFFF;
	}

      </style>
   </head>

   <body bgcolor="#A3CCA3">
      <div class="centered">
   <?php if(!isset($_SESSION["init"])) { ?>

         <p>We're sorry but your session has expired.</p>
         <p>Please login.</p>
         <form method="post" action="login.php">
            <input type="submit" value="login"/>
         </form>

   <?php } elseif(isset($good_pass) and !$good_pass) { ?>

         <p>Passwords did not match.</p>
         <form method="post" action="edit_info.php">
            <input type="submit" value="Try again"/>
         </form>

   <?php } else { ?>

      <form method="post" action="edit_info.php">
         E-mail: <input type="text" name="email" value="<?= $_SESSION['email'] ?>"/><br/>
         Password: <input type="password" name="password" value="<?= $_SESSION['pass'] ?>"/><br/>
         Confirm Password: <input type="password" name="password2" value="<?= $_SESSION['pass'] ?>"/><br/>
         Email Notification
         <?php if($_SESSION["notify"] === "1") { ?>
            <input type="radio" name="notify" value="1" checked="checked">Yes</intput>
            <input type="radio" name="notify" value="-1">No</intput><br/>
         <?php } else { ?>
            <input type="radio" name="notify" value="1">Yes</intput>
            <input type="radio" name="notify" value="-1" checked="checked">No</intput><br/>
         <?php } ?>         
         <input type="submit" value="Request Update"/>
         <input type="hidden" name="form_type" value="update"/>
      </form>

   <?php } ?>
      </div>
   <?php include "log_button.php"; ?>

   </body>

</html>
