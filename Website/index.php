<html>
	<?php
//	error_reporting(E_ALL);
//	ini_set('display_errors', 'On');



	include 'bookSlider.php';
	include 'log_button.php';
	include 'navi.php';
	?>
    <head>
        <title>LMS</title>
		<style>
		div.content{
			background-color: #FFFFFF;
			text-align: left;
			width: 550px;
			padding: 10px;
			border-radius: 10px;
			margin-left: 165px;
			margin-right: auto;
		}

		#button{
			position:absolute;
			top:10;
			right:10;
		}
         body {
            background-color: #A3CCA3;
         }

		</style>
    </head>
    <body>
		<div class="content">
        <p>Welcome to the Library Management System</p>
            <form method="get" action="/search.php">
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
		</div>
    </body>
</html>

