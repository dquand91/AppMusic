<?php
	// Đây là file khởi tạo kết nối với dataBase.
	// 4 thông số bên dưới là thông số khai báo kết nối.
	$hostname = "localhost";
	$username = "id6334390_dquand91";
	$password = "Ilovemyself1991";
	$databasename = "id6334390_appdemomusic";

	// Khởi tạo 1 kết nối tới SQL và truyền 4 thông số vào
	// mysqli_connect để tạo kết nối và giữ kết nối trong biến $myConnect
	$myConnect = mysqli_connect($hostname, $username, $password, $databasename);

	// mysqli_query để tiến hành query. Tham số cần là: mysqli_connect và câu query
	// Dùng cái này để có thể sử dụng tiếng Việt trong SQL
	mysqli_query($myConnect, "SET NAMES 'utf8'");

?>