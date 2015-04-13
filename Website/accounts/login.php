<html>

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
?>

<body>
<div class="login">
   	<form action="view_account.php" method="post">
      	User name: <input type="text" name="user_name"> <br/>
      	Password: <input type="password" name="password"> <br/>
      	<input type="submit" value="Log In"/>
      	<input type="hidden" name="form_type" value="login"/>
	</form>
   		<form action="create_account.php">
		<input type="submit" value="Create new account">
   	</form>
</div>
</body>

</html>
