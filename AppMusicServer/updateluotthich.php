<?php
	require "connect.php";

	$luotthich = $_POST['luotthich'];
	$idbaihat = $_POST['idbaihat'];

	$query = "SELECT LuotThich FROM baihat WHERE idBaiHat = '$idbaihat'";
	$dataluotthich = mysqli_query($myConnect,$query);
	$row = mysqli_fetch_assoc($dataluotthich);
	$tongluotthich = $row['LuotThich'];

	if (isset($luotthich)) {
		$tongluotthich = $tongluotthich + $luotthich;
		$querysum = "UPDATE baihat SET LuotThich = '$tongluotthich' WHERE idBaiHat = '$idbaihat'";
		$dataupdate = mysqli_query($myConnect,$querysum);
		if ($dataupdate) {
			echo "Success";
		}else{
			echo "Fail";
		}
	}
?>