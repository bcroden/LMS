<html>
	<?php
<<<<<<< HEAD
	error_reporting(E_ALL);
	ini_set('display_errors', 'On');


=======
>>>>>>> a84709e0558cfd2fcb2d57098f701214b6be5147
	include 'bookSlider.php';
	?>
    <head>
        <title>LMS</title>
		<style>

		#button{
			position:absolute;
			top:10;
			right:10;
		}

		</style>
    </head>
    <body>
        <p>Welcome to the Library Management System</p>
		<form id="button" action="accounts/login.php">
			<input type="submit" value="Go to Login">
		</form>
        <p>
            <form method="post" action="/search.php">
				<select name="type">
					<option value="isbn">ISBN</option>
					<option value="title">Title</option>
					<option value="author">Author</option>
					<option value="genre">Genre</option>
				</select>
                Search for a book: <input type="text" name="search">
                <input type="submit" value="Search">
            </form>
        </p>
    </body>
</html>

