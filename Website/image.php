<!DOCTYPE html>
<?php
		$image = imagecreatefromstring(file_get_contents($row["picURL"]));
		$type = exif_imagetype($row["picURL"]);
		$width = imagesx($image);
		if($type === 2){
			ob_start();
			imagejpeg($image);
			printf('<img src="data:image/png;base64,%s"/>', base64_encode(ob_get_clean()));
			imagedestroy($image);
		}
		elseif($type === 3){
			ob_start();
			imagepng($image);
			printf('<img src="data:image/png;base64,%s"/>', base64_encode(ob_get_clean()));
			imagedestroy($image);
		}
?>
