<?php

echo <<< EOT
<style>
	div.pane{
		position: fixed;
		background-color: #FFFFFF;
		border-radius: 10px;
		text-align: center;
		width: 200px;
		padding-top: 10px;
		padding-left: 10px;
		padding-right: 10px;
		padding-bottom: 10px;
	}

	div.part{
		cursor: pointer;
		border-radius: 10px;
	}
	div.part:hover{
		background-color: #BDBDBD;
	}

	div.placeholder{
		border-top: 1px solid black;
	}
</style>
EOT;

echo "<body bgcolor='#A3CCA3'>";
echo <<< EOT

	<div class=pane>
		<div class=placeholder></div>
		<form class=part id=gohome action=index.php method=post>
			<div class=part onclick=document.getElementById('gohome').submit();>SEARCH</div>
		</form>
		<div class=placeholder></div>
		<form id=goaccount action=view_account.php method=post>
            <div class=part onclick=document.getElementById('goaccount').submit();>ACCOUNT</div>
        </form>
		<div class=placeholder></div>
		<form id=goedit action=edit_info.php method=post>
            <div class=part onclick=document.getElementById('goedit').submit();>EDIT INFO</div>
        </form>
		<div class=placeholder></div>
	</div>

EOT;

echo "</body>";
?>
