<?php
	require "connect.php";
	class Baihat{
		function Baihat($idbaihat,$tenbaihat,$hinhbaihat,$casi,$linkbaihat,$luotthich){
			$this->Idbaihat = $idbaihat;
			$this->Tenbaihat = $tenbaihat;
			$this->Hinhbaihat = $hinhbaihat;
			$this->Casi = $casi;
			$this->Linkbaihat = $linkbaihat;
			$this->Luotthich = $luotthich;
		}
	}

	$arraydanhsachbaihat = array();
	
	if(isset($_POST['idalbum'])){
	    $idalbum = $_POST['idalbum'];
    	$query = "SELECT * FROM baihat WHERE FIND_IN_SET('$idalbum',idAlbum)";
	}

	
    if (isset($_POST['idtheloai'])) {
		$idtheloai = $_POST['idtheloai'];
		$query = "SELECT * FROM baihat WHERE FIND_IN_SET('$idtheloai',idTheLoai)";
	}

	if (isset($_POST['idplaylist'])) {
		$idplaylist =$_POST['idplaylist'] ;
		// FIND_IN_SET sẽ lọc trong cột mong muốn ra chính xác giá trị mình truyền vào.
		// Ví dụ ở đây là: lấy đúng giá trị của biến $idplaylist trong cột idPlayList. Cụ thể: $idplaylist = 3 => chỉ trả ra row có giá trị = 3 trong cột "idPlayList" chứ ko trả ra row có giá trị = 13, 33, 333,..
		$query = "SELECT * FROM baihat WHERE FIND_IN_SET('$idplaylist',idPlayList)";
	}

	// isset kiểm tra có tồn tại hay không.
	// nghĩa của câu điều kiện: kiểm tra trong POST của client gửi lên có param "idquangcao" hay không?
	if (isset($_POST['idquangcao'])) {
		$idquangcao = $_POST['idquangcao'];
		$queryquangcao = "SELECT * FROM quangcao WHERE Id ='$idquangcao'";
		$dataquangcao = mysqli_query($myConnect,$queryquangcao);
		$rowquangcao = mysqli_fetch_assoc($dataquangcao);
		$id = $rowquangcao['idBaiHat'];
		$query = "SELECT * FROM baihat WHERE IdBaiHat = '$id'";
	}
	
	$data = mysqli_query($myConnect,$query);
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($arraydanhsachbaihat, new Baihat($row['idBaiHat']
													,$row['TenBaiHat']
													,$row['HinhBaiHat']
													,$row['CaSi']
													,$row['LinkBaiHat']
													,$row['LuotThich']));
	}

	echo json_encode($arraydanhsachbaihat);
?>