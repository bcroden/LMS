<!DOCTYPE html>
<html>
    <head>
        <title>LMS</title>
		<style>
		div.content{
			position: relative;
        	background-color: #FFFFFF;
        	border-radius: 10px;
        	padding-top: 10px;
			padding-left: 10px;
			padding-right: 10px;
			padding-bottom: 1px;
        	width: 50%;
        	margin-left: auto;
        	top: 45px;
        	margin-right: auto;
		}
		div.btn{
			text-align: center;
			padding-top: 10px;
		}
		</style>
<?php
include 'bookSlider.php';
include 'navi.php';
include 'log_button.php';
?>

    </head>
    <body bgcolor="#A3CCA3">
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
				<div class="btn">
                <input  type="submit" style="width:200px;height:50px;" value="Search">
				</div>
            </form>
        </p>
		</div>
    </body>
</html>

