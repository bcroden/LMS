<?php
echo <<< EOT

<style>
	div.side{
	 	background:lightblue;
		position: fixed;
		border: solid 1px black;
		right:5px;
		top: 60px;
		width:200px;
		padding: 10px;
		border-radius: 10px;
		vertical-align: top;
	}

	div.book{
		border-top: solid 1px black;
		padding-top: 5px;
		padding-bottom: 5px;
		text-align: center;
        margin: auto;
		cursor: pointer;
	}

	div.image{
		padding-top: 5px;
		text-align: center;
		margin: auto;
	}
</style>
EOT;

		include 'DBwrapper.php';
		connect();
		echo "<body>";
		echo "<div class=side>";
		echo "Our newest Selections: <br>";
		$result = top(2);
		$i = 0;
while($row = mysqli_fetch_array($result)){
echo <<< EOT
<form id=$i  action=bookInfo.php method=post>
    <input type=hidden name=isbn value=$row[isbn]>
        <div class=book onclick=document.getElementById($i).submit();>
        $row[title]
EOT;
                    if(!ctype_space($row['picURL'])){
						echo "<div class=image>";
                        include 'image.php';
						echo "</div>";
                    }
                echo "</div>";
                echo "</form>";
                $i++;
            }
            echo "</div>";
		echo "</body>";
?>
