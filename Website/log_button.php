<?php
   session_start();
   $root = basename($_SERVER["DOCUMENT_ROOT"]);

   if(isset($_POST["logout"])) {
      session_unset();
      session_destroy();
      header("Location: " . $_POST["return"]);
   }

   if(isset($_SESSION["init"])) {
?>

<style>
   div.acc_menu {
      position: relative;
   }

   div.acc_menu ul {
      margin: 0;
      padding: 0;
      position: absolute;
      height: 100%;
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
      width: 160px;
      background-color: blanchedalmond;
   }

   div.acc_menu li:hover > ul {
      display: block;
   }

   div.acc_menu ul ul li:hover {
      position: relative;
      float: none;
      display: block;
      width: 100%;
   }
</style>
<div class="acc_menu" style="position:fixed;top:10px;right:100px;">
   <ul>
      <li>
         <a href="view_account.php"><?= $_SESSION["usernm"] ?></a>
         <ul>
            <li>
               <form id="log" action="log_button.php" method="post">
                  <input type="hidden" name="logout" value="yes"/>
                  <input type="hidden" name="return" value="<?= $_SERVER['PHP_SELF'] ?>"/>
                  <input type="submit" value="logout"/>
               </form>
            </li>
         </ul>
      </li>
   </ul>
</div>

<?php
   }
?>

