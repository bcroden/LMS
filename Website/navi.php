<?php

echo <<< EOT
<style>
	div.pane{
		position: fixed;
		background-color: #FFFFFF;
		border-radius: 10px;
		text-align: center;
		width: 125px;
		padding-top: 15px;
		padding-left: 10px;
		padding-right: 10px;
		padding-bottom: 1px;
	}

	div.part{
		cursor: pointer;
		border-radius: 10px;
	}
	div.part:hover{
		background-color: #BDBDBD;
	}
</style>
EOT;

echo "<body bgcolor='#A3CCA3'>";
echo <<< EOT

	<div class=pane>
		<form class=part id=gohome action=index.php method=post>
			<div class=part onclick=document.getElementById('gohome').submit();>SEARCH</div>
		</form>
		<form id=goaccount action=view_account.php method=post>
            <div class=part onclick=document.getElementById('goaccount').submit();>ACCOUNT</div>
        </form>
	</div>

EOT;

echo "</body>";
?>
