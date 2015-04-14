<!DOCTYPE html>
<html>
<head>
   <title>LMS</title>
<style>

	div.login{
		position: absolute;
		border: solid 1px black;
		border-radius: 10px;
		padding: 10px;
		margin-left: 45px;
		top: 45px;
		margin-right: auto;
		width: 50%;
	}

</style>

<?php
	include '../bookSlider.php';

   if(!isset($_POST["return"]))
      $_POST["return"] = "view_account.php";

   //the form below was submitted, validate it
   if(isset($_POST["form_type"]) && $_POST["form_type"] === "login") {
      $login_okay = false;

      $usrnm = $_POST["user_name"];
      $pswd = $_POST["password"];

      $result = getPass($usrnm);
      if(!($result === -1)) {
         while($row = mysqli_fetch_array($result)) {
            if($pswd === $row["password"]) {
               $login_okay = true;
            }
         }
      }

      //if the login credentials are valid start a session
      if($login_okay) {
         session_start();

         $usr_inf = mysqli_fetch_array(getUserInfo($usrnm));
         $_SESSION["init"] = true;
         $_SESSION["fname"] = $usr_inf["fname"];
         $_SESSION["lname"] = $usr_inf["lname"];
         $_SESSION["email"] = $usr_inf["email"];
         $_SESSION["notify"] = $usr_inf["notify"];
         $_SESSION["usernm"] = $usrnm;
         $_SESSION["pass"] = $pswd;
         if(isset($_POST["isbn"]))
            $_SESSION["isbn"] = $_POST["isbn"];
      }
   }

   //The session has been started return to the original page
   if(isset($_SESSION["init"]))
      header("Location: " . $_POST["return"]);
?>
</head>
<body>
   <div class="login">
      <form action="login.php" method="post">
         User name: <input type="text" name="user_name"> <br/>
      	 Password: <input type="password" name="password"> <br/>
      	 <input type="submit" value="Log In"/>
         <input type="hidden" name="form_type" value="login"/>
      	 <input type="hidden" name="return" value="<?= $_POST['return'] ?>"/>
         <?php if(isset($_POST["isbn"])) { ?>
            <input type="hidden" name="isbn" value="<?= $_POST['isbn'] ?>"/>
         <?php } ?>
      </form>

      <?php if(isset($login_okay) && $login_okay == false) { ?>

      <p style="color:red">Incorrect User name or Password</p>

      <?php } ?>

      <form action="create_account.php">
         <input type="submit" value="Create new account">
      </form>
   </div>
</body>

</html>
