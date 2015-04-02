<html>
   <form action="view_account.php" method="post">
      User name: <input type="text" name="user_name"> <br/>
      Password: <input type="password" name="password"> <br/>
      <input type="submit" value="Log In"/>
      <input type="hidden" name="form_type" value="login"/>
   </form>
   <form action="create_account.php">
		<input type="submit" value="Create new account">
   </form>
</html>
