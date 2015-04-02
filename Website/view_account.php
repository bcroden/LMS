<?php
   include "DBwrapper.php";
   error_reporting(E_ALL);
   ini_set('display_errors', 'On');

   echo "Hello";

   connect();

   if(isset($_POST["form_type"]) && !empty($_POST["form_type"])) {
      $usrnm = "";
      $pswd = "";
      $okay = false;

      if($_POST["form_type"] == "creation") {
         /* Attempt to create a new patron account */
         $err = "";
//         $err = create_account($usrnm, $pswd);
         if(!empty($err))
            creation_fail($err);
         else
            $okay = true;
      }
      elseif($_POST["form_type"] == "login") {
         /* Attempt to log the user into an existing patron's account */
         $db_pass = getPass($usrnm);
      }

      if($okay)
         display_account($usrnm, $pswd);
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

         //make sure that passwords are not empty and match
         $pswd = $_POST["password"];
         $pswd2 = $_POST["password2"];
         if(empty($pswd) || empty($pswd2))
            $s_err = $s_err . "<p>Please enter your password into both fields</p>";
         elseif($pswd !== $pswd2)
            $s_err = $s_err . "<p>The passwords entered do not match</p>";

         if(empty($s_err)) {
            //TODO: DB call to add patron to tables
         }

         return $s_err;
      } //end if(!empty($_POST))

      return "<p>No data was submitted. Please try again</p>";
   }//end create_account

   function display_account($usrnm, $pswd) {
?>
<!DOCTYPE html>
<html>
   <body>
      <p>Displaying account</p>
   </body>
</html>
<?php
   } //end of display_account

   function creation_fail($err) {
?>
<!DOCTYPE html>
<html>
   <body>
      <p>Account Creation Failed</p>
      <?= $err ?>
      <form action="create_account.php">
         <input type="submit" value="Try Again"/>
      </form>
   </body>
</html>
<?php
   } //end of creation_fail

   function login_fail() {
?>
<!DOCTYPE html>
<html>
   <body>
      <p>Login Attempt Failed</p>
      <p>User name or password are incorrect</p>
      <form action="login.php">
         <input type="submit" value="Try Again"/>
      </form>
   </body>
</html>
<?php
   } //end of login_fail
?>