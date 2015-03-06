<?php

    $host = "cadeg-lms-1330962";
    $user = "cadeg";
    $pass = "";
    //database not yet created, placeholder variable/name
    $db = "LMS";
    $port = "3306";

    //setup connection to be referenced
    $connection = mysqli_connect($host, $user, $pass, $db, $port)or die(mysql_error);
    
    if ($connection->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    } 
    
    echo "Pre query" . "<br>";
    
    $sql = "SELECT id, isbn, title FROM book";
    $result = $connection->query($sql);
    
    if($result->num_rows > 0){
        //output data
        while($row = $result->fetch_assoc()){
            echo "id: " . $row["id"] . " isbn: " . $row["isbn"] . " title: " . $row["title"] . "<br>";
        }
    }
    else{
        echo "Error or something...I don't know <br>";
    }
    //adjust query for testing
    

?>
