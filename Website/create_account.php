<?php

   include "bookSlider.php";

   if(isset($_POST["form_type"])) {
      $okay = false;
      $err = "";
      $usrnm = $_POST["user_name"];
      $pswd = $_POST["password"];
      $err = create_account($usrnm, $pswd);
      if(!empty($err))
         creation_fail($err);
      else {
         session_start();
         $okay = true;
         $usr_inf = mysqli_fetch_array(getUserInfo($usrnm));
         $fn = $usr_inf["fname"];
         $ln = $usr_inf["lname"];
         $em = $usr_inf["email"];
         $nt = $usr_inf["notify"];
         $_SESSION["init"] = true;
         $_SESSION["fname"] = $fn;
         $_SESSION["lname"] = $ln;
         $_SESSION["email"] = $em;
         $_SESSION["notify"] = $nt;
         $_SESSION["usernm"] = $usrnm;
         $_SESSION["pass"] = $pswd;
      }

      if($okay)
        header("Location: view_account.php");
   }

   function create_account(&$usrnm, &$pswd) {
      if(!empty($_POST)) {
         $fname = "";
         $lname = "";
         $email = "";
         $s_err = "";

         //make sure that first and last names are not empty
         $fname = $_POST["first_name"];
         if(empty($fname))
            $s_err = $s_err . "<p>Please enter your first name</p>";

         $lname = $_POST["last_name"];
         if(empty($lname))
            $s_err = $s_err . "<p>Please enter your last name</p>";

         $email = $_POST["email"];
         if(empty($email))
            $s_err = $s_err . "<p>Please enter your email address</p>";

         $usrnm = $_POST["user_name"];
         if(empty($usrnm))
            $s_err = $s_err . "<p>Please enter a user name</p>";
         //TODO: add code to ensure that the user name is unique with the LMS' database
		 $result = getUsers();
			while($row = mysqli_fetch_array($result)){
				if($usrnm === $row["username"]){
					//return to create due to this stuff
					header("Location: /create_account.php");
				}
			}
         //make sure that passwords are not empty and match
         $pswd = $_POST["password"];
         $pswd2 = $_POST["password2"];
         if(empty($pswd) || empty($pswd2))
            $s_err = $s_err . "<p>Please enter your password into both fields</p>";
         elseif($pswd !== $pswd2)
            $s_err = $s_err . "<p>The passwords entered do not match</p>";

         if(empty($s_err)) {
			addUser($_POST["user_name"], $_POST["password"], $_POST["email"], $_POST["first_name"], $_POST["last_name"], $_POST["notify"], 1);
         }

         return $s_err;
      } //end if(!empty($_POST))

      return "<p>No data was submitted. Please try again</p>";
   }//end create_account


?>

<!DOCTYPE html>
<html>
   <head>
      <title>LMS</title>
      <script>
         function validate() {
            var form = document.forms[0];
            var pass1 = form["password"].value;
            var pass2 = form["password2"].value;
            return true;
            if(pass1 == pass2)
               return true;
            else
               return false;
         }
      </script>
      <style>
         body {
            background-color: #A3CCA3;
         }
	div.centered{
		position: absolute;
		border-radius: 10px;
		padding: 10px;
		margin-left: 45px;
		top: 45px;
		margin-right: auto;
		width: 50%;
           background-color: #FFFFFF;
	}
      </style>
   </head>
   <body>
    <div class="centered">
      <form action="create_account.php" method="post" onsubmit="return validate()">
         First Name: <input type="text" name="first_name"/><br/>
         Last Name: <input type="text" name="last_name"/><br/>
         E-mail: <input type="text" name="email"/><br/>
         Username: <input type="text" name="user_name"/><br/>
         Password: <input type="password" name="password"/><br/>
         Confirm Password: <input type="password" name="password2"/><br/>
         Email Notification <input type="radio" name="notify" value="1">Yes</intput>
         <input type="radio" name="notify" value="-1">No</intput><br/>
         <input type="submit" value="Create Account"/>
         <input type="hidden" name="form_type" value="creation"/>
      </form>
    </div>
<!--    <?php include "log_button.php"; include "bookSlider.php";?> -->
   </body>
</html>
