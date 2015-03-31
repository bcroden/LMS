<html>
    <head>
        <title>LMS</title>
    </head>
    <body>
        <p>Welcome to the Library Management System</p>
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
