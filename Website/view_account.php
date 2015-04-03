<?php
   include "DBwrapper.php";
   error_reporting(E_ALL);
   ini_set('display_errors', 'On');

   connect();

   if(isset($_POST["form_type"]) && !empty($_POST["form_type"])) {
		$usrnm = $_POST["user_name"];
		$pswd = $_POST["password"];
      $okay = false;

      if($_POST["form_type"] === "creation") {
         /* Attempt to create a new patron account */
         $err = "";
         $err = create_account($usrnm, $pswd);
         if(!empty($err))
            creation_fail($err);
         else
            $okay = true;
      }
      elseif($_POST["form_type"] === "login") {
         /* Attempt to log the user into an existing patron's account */
         $result = getPass($usrnm);
         if($result === -1){
            login_fail();
         }
         else {
            while($row = mysqli_fetch_array($result)) {
               if($pswd === $row["password"]) {
                  display_account($usrnm, $pswd);
               }
               else {
                  login_fail();
               }
            }
         }
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
			addUser($_POST["user_name"], $_POST["password"], $_POST["email"], $_POST["first_name"], $_POST["last_name"], $_POST["notify"], 1);
         }

         return $s_err;
      } //end if(!empty($_POST))

      return "<p>No data was submitted. Please try again</p>";
   }//end create_account

   function display_account($usrnm, $pswd) {

   $usr_inf = mysqli_fetch_array(getUserInfo($usrnm))
?>
<!DOCTYPE html>
<html>
   <head>
      <title>LMS</title>
   </head>
   <body>
      <h3>Personal and Contact Information</h3>
      <table>
         <tr>
            <th>First Name</th>
            <th>Last Name</th>
         </tr>
         <tr>
            <td><?= $usr_inf["fname"] ?></td>
            <td><?= $usr_inf["lname"] ?></td>
         </tr>
         <tr>
            <th>Email</th>
            <th>Notificiation</th>
         </tr>
         <tr>
            <td><?= $usr_inf["email"] ?></td>
            <td><?php if($usr_inf["notify"] == 1) echo "Yes"; else echo "No"; ?></td>
         </tr>
      </table>

      <h3>Books Currently Checked-Out</h3>
      <table>
         <tr>
            <th>ISBN</th>
            <th>Title</th>
            <th>Author</th>
         </tr>
          <?php $books_out_res = mysqli_fetch_array(getBooksOut($usrnm));
             $books_out = $books_out_res["booksout"];
             if(!empty($books_out)) {
                $bk_out_arr = explode(",", $books_out);
                for($i=0; $i < count($bk_out_arr); $i++) {
                   if(empty($bk_out_arr[$i])) continue;
                   $temp = mysqli_fetch_array(queryISBN($bk_out_arr[$i]));
                   echo "<tr><td>" . $temp["isbn"] . "</td><td>" . $temp["title"] . "</td><td>" . $temp["author"] . "</td><td>" . calcTime($usrnm, $temp["isbn"]) . "</td></tr>";
                }
             }
          ?>
      </table>
   </body>
</html>
<?php
   } //end of display_account

   function creation_fail($err) {
echo <<< EOT
<!DOCTYPE html>
<html>
   <body>
      <p>Account Creation Failed</p>
      $err
      <form action="create_account.php">
         <input type="submit" value="Try Again"/>
      </form>
   </body>
</html>
EOT;
   } //end of creation_fail

   function login_fail() {
header("Location: /login.php");
/*echo <<< EOT
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
EOT;*/
   } //end of login_fail
?>
