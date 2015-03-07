
<!-- We don't need this because I redirected home.php to reference my page that queries
     the db.  
     User story 1.1 completed!
     -->

<headh>
    <body>
        <p>Search page.</p>
        <p>
            <p>You searched for <?php echo $_POST['search']; ?></p>
            <form method="post" action="https://lms-ctr002.c9.io/Website/home.php">
        		<input type="submit" value="Return to home page.">
        	</form>
        	<form method="post" action="https://lms-ctr002.c9.io/Website/search.php">
        		Search for a book: <input type="text" name="search">
        		<input type="submit" value="Search">
        	</form>
	    </p>
    </body>
</headh>