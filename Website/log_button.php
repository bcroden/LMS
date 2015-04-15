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

<form style="position:absolute;top:10px;right:30px;" action="/log_button.php" method="post">
   <input type="hidden" name="logout" value="yes"/>
   <input type="hidden" name="return" value="<?= $_SERVER['PHP_SELF'] ?>"/>
   <a href="/accounts/view_account.php"><?= $_SESSION["usernm"] ?></a> <input type="submit" value="logout"/>
</form>

<?php
   } else {
?>

<form style="position:absolute;top:10px;right:30px;" action="<?= '/accounts/login.php' ?>" method="post">
   <input type="submit" value="Login"/>
</form>

<?php
   }
?>
