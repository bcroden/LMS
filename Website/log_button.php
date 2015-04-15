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

<form style="position:fixed;top:10px;right:100px;" action="log_button.php" method="post">
   <input type="hidden" name="logout" value="yes"/>
   <input type="hidden" name="return" value="<?= $_SERVER['PHP_SELF'] ?>"/>
   <a href="view_account.php"><?= $_SESSION["usernm"] ?></a> <input type="submit" value="logout"/>
</form>

<?php
   } else {
?>

<form style="position:fixed;top:10px;right:100px;" action="<?= 'login.php' ?>" method="post">
   <input type="submit" value="Login"/>
</form>

<?php
   }
?>

<form style="position:fixed;top:10px;right:10px;" action="index.php" method="post">
    <input type="submit" value="Home"/>
</form>

