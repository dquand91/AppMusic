<?php 

	require "connect.php";

	// Cau query bên dưới:
	// DISTINCT: lấy ra 1 bảng result ko trùng nhau.
	// ORDER BY rand(): sắp xếp cái bảng result theo trật tự random
	// LIMIT 3: chỉ lấy ra tối đa 3 dòng.
	// Ý nghĩa câu query: lấy ra 1 bảng result có các dòng không trùng nhau, từ bảng "playlist" và sắp xếp bảng result đó theo thứ tự ngẫu nhiên, chỉ lấy ra tối đa 3 dòng.
	$query = "SELECT DISTINCT * FROM playlist ORDER BY rand(" . date("Ymd") . ") LIMIT 3";
	

	class PlayListToday{
		function PlayListToday($idPlayList, $tenPlayList, $hinhAnh, $icon){
			$this->IdPlayList = $idPlayList;
			$this->TenPlayList = $tenPlayList;
			$this->HinhAnh = $hinhanh;
			$this->Icon = $icon;
		}
	}

	$arrayPlayListForToday = array();
	$data = mysqli_query($myConnect,$query);
	while($row = mysqli_fetch_assoc($data)){
		array_push($arrayPlayListForToday, new PlayListToday($row['idPlayList']
																,$row['Ten']
																,$row['HinhNen']
																,$row['HinhIcon']));
		echo json_encode($arrayPlayListForToday);
	}

 ?>