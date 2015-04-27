<?php
   session_start();
   $root = basename($_SERVER["DOCUMENT_ROOT"]);

<<<<<<< HEAD
   if(isset($_POST["logout"])){
=======
   if(isset($_GET["logout"])) {
>>>>>>> 21839bb9ac53e3e02e864772eea1f6ec5484e8e1
      session_unset();
      session_destroy();
      header("Location: " . $_GET["return"]);
   }

<<<<<<< HEAD
   if(isset($_SESSION["init"])){
=======
/*   if(isset($_POST["logout"])) {
      session_unset();
      session_destroy();
      header("Location: " . $_POST["return"]);
   }
*/
   if(isset($_SESSION["init"])) {
>>>>>>> 21839bb9ac53e3e02e864772eea1f6ec5484e8e1
?>

<style>
   div.acc_menu {
      position: fixed;
      top: 10px;
      right: 10px;
      height: 15px;
      width: 120px;
      padding: 5px;
      border-radius: 10px;
      background-color: #FFF;
   }

   div.acc_menu ul {
      margin: 0;
      padding: 0;
      position: absolute;
      height: 100%;
      width: 100%;
      list-style-type: none;
   }

   div.acc_menu ul li {
      display: block;
      float: left;
      height: auto;
      width: 120px;
   }

   div.acc_menu ul ul {
      display: none;
      text-align: center;
   }

   div.acc_menu li:hover > ul {
      display: block;
   }

   div.acc_menu ul ul li:hover {
      position: relative;
      float: none;
      display: block;
   }

   div.acc_menu ul ul li {
      background-color: #FFF;
      border-radius: 10px;
      border-top: 1px solid;
   }
</style>
<div class="acc_menu">
   <ul>
      <li>
         <a href="view_account.php"><?= $_SESSION["usernm"] ?></a>
         <ul>
            <li>
               <a href="view_account.php?logout=yes&return=<?= $_SERVER['PHP_SELF'] ?>">Log out</a>
               <!--<form id="log" action="log_button.php" method="post">
                  <input type="hidden" name="logout" value="yes"/>
                  <input type="hidden" name="return" value="<?= $_SERVER['PHP_SELF'] ?>"/>
                  <input type="submit" value="logout"/>
               </form>-->
            </li>
         </ul>
      </li>
   </ul>
</div>

<?php
   }
?>

