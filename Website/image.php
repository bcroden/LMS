<?php
		$image = imagecreatefromjpeg($row["picURL"]);
        ob_start();
        imagejpeg($image);
        printf('<img src="data:image/png;base64,%s"/>', base64_encode(ob_get_clean()));
        imagedestroy($myImage);
?>
