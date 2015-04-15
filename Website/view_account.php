<?php
   session_start();
//   error_reporting(E_ALL);
//   ini_set('display_errors', 'On');
   if(!isset($_SESSION["init"])) { header("Location: login.php");/*session_start();*/ }
   if(isset($_POST["logout"])) {
      session_destroy();
      header("Location: login.php");
   }

   include "bookSlider.php";
//   include "DBwrapper.php";

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

      if($okay) {
         display_account($usrnm, $pswd);
      }
   }
   elseif(isset($_SESSION["init"])) {
      if(isset($_POST["new_email"])) {
         changeEmail($_SESSION["usernm"], $_POST["new_email"]);
         $_SESSION["email"] = $_POST["new_email"];
      }
      if(isset($_POST["new_password"])) {
         setNewPass($_SESSION["usernm"], $_SESSION["pass"], $_POST["new_password"]);
         $_SESSION["pass"] = $_POST["new_password"];
      }
      if(isset($_POST["notify"])) {
         setNotification($_SESSION["usernm"], $_POST["notify"]);
         $_SESSION["notify"] = $_POST["notify"];
      }
      display_account($_SESSION["usernm"], $_SESSION["pass"]);
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

   function display_account($usrnm, $pswd) {


      if(!isset($_SESSION["init"])) {
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

?>
<!DOCTYPE html>
<html>
   <head>
      <title>LMS</title>
      <script src="view_account.js"></script>
      <link rel="stylesheet" type="text/css" href="view_account.css">
   </head>
   <body>
    <div class="nice">
      <h3>Personal and Contact Information</h3>
      <div id="tglBtn"><button id="ContactButton" onclick="toggleContact()">View</button></div>
      <table id="ContactTable" hidden="false">
         <tr>
            <th>First Name</th>
            <th>Last Name</th>
         </tr>
         <tr>
            <td><?= $_SESSION["fname"] ?></td>
            <td><?= $_SESSION["lname"] ?></td>
         </tr>
         <tr>
            <th>Email</th>
            <th>Notificiation</th>
         </tr>
         <tr>
            <td><?= $_SESSION["email"] ?></td>
            <td><?php if($_SESSION["notify"] == 1) echo "Yes"; else echo "No"; ?></td>
         </tr>
         <tr>
            <td colspan="2">
               <form action="edit_info.php" mehtod="post">
                  <input type="submit" value="Edit Personl Information"/>
               </form>
            </td>
         </tr>
      </table>

      <h3>Books Currently Checked-Out</h3>
      <div id="tglBtn"><button id="BookOutButton" onclick="toggleBookOut()">View</button></div>
      <table id="BookOutTable" hidden="true">
         <tr>
            <th>ISBN</th>
            <th>Title</th>
            <th>Author</th>
            <th>Time Left</th>
         </tr>
          <?php $books_out_res = mysqli_fetch_array(getBooksOut($usrnm));
             $books_out = $books_out_res["booksout"];
             if(!empty($books_out)) {
                $bk_out_arr = explode(",", $books_out);
                for($i=0; $i < count($bk_out_arr); $i++) {
                   if(empty($bk_out_arr[$i])) continue;
                   $temp = mysqli_fetch_array(queryISBN($bk_out_arr[$i]));
                   echo "<tr><td>" . $temp["isbn"] . "</td><td>" . $temp["title"] . "</td><td>" . $temp["author"] . "</td><td>" . floor(calcTime($usrnm, $temp["isbn"])) . " days</td></tr>";
                }
             } else {
                echo "<tr><td colspan='4'>You currently do not have any books checked out.</td></tr>";
             }
          ?>
      </table>
      
      <h3>Books Reserved</h3>
      <div id="tglBtn"><button id="BookResButton" onclick="toggleBookRes()">View</button></div>
      <table id="BookResTable" hidden="true">
         <tr>
            <th>ISBN</th>
            <th>Title</th>
            <th>Author</th>
         </tr>
          <?php $books_res_arr = mysqli_fetch_array(getReserved($usrnm));
             $books_res = $books_res_arr["booksr"];
             if(!empty($books_res)) {
                $bk_res_arr = explode(",", $books_res);
                for($i=0; $i < count($bk_res_arr); $i++) {
                   if(empty($bk_res_arr[$i])) continue;
                   $temp = mysqli_fetch_array(queryISBN($bk_res_arr[$i]));
                   echo "<tr><td>" . $temp["isbn"] . "</td><td>" . $temp["title"] . "</td><td>" . $temp["author"] . "</td></tr>";
                }
             } else {
               echo "<tr><td colspan='3'>You currently do not have any books reserved.</td></tr>";
             }
          ?>
      </table>
    </div>
      <?php include "log_button.php"; ?>

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
      header("Location: login.php");
   } //end of login_fail
?>
