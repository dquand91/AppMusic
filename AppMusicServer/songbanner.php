<?php 

	require "connect.php"; //liên kết với file connect.php để lấy ra biến $myConnect

	// Đây là câu query
	// Câu query sẽ gọp 2 bảng "baihat" và "quangcao" 
	// => Dựa vào cái key chung "idBaiHat" của 2 bảng trên.
	// => tạo ra 1 bảng chứa các thông tin: quangcao.id, quangcao.HinhAnh, quangcao.NoiDung, quangcao.idBaiHat, baihat.TenBaiHat, baihat.HinhBaiHat
	$query = "SELECT quangcao.id, quangcao.HinhAnh, quangcao.NoiDung, quangcao.idBaiHat, baihat.TenBaiHat, baihat.HinhBaiHat FROM `baihat` INNER JOIN quangcao ON quangcao.idBaiHat = baihat.idBaiHat WHERE quangcao.idBaiHat = baihat.idBaiHat";

	// tiến hành query và lưu kết quả vào $data.
	$data = mysqli_query($myConnect, $query);

	/**
	* Object để chứa thông tin dữ liệu có được từ query. Cũng là cấu trúc để trả JSON.
	*/
	class QuangCao
	{		
		// Constructor của object QuangCao
		// Gồm các thuộc tính như bên dưới.
		function QuangCao($idquangcao, $hinhanh, $noidung, $idBaiHat, $TenBaiHat, $HinhBaiHat){
			$this->IdQuangCao = $idquangcao;
			$this->HinhAnh = $hinhanh;
			$this->NoiDung = $noidung;
			$this->IdBaiHat = $idBaiHat;
			$this->TenBaiHat = $TenBaiHat;
			$this->HinhBaiHat = $HinhBaiHat;			
		}
	}

	$mangquangcao = array(); // mảng để chứa các object QuangCao lấy ra từ query SQL

	// Duyệt từng dòng trong bảng result của query SQL
	while ($row = mysqli_fetch_assoc($data)) {
		// add các object vào trong mảng
		// $row['id'] => phải điền đúng tên cột trong SQL. Ở đây điền đúng cột "id"
		// $row['NoiDung'] => Ở đây điền đúng cột "NoiDung"
		array_push($mangquangcao, new QuangCao($row['id']
												,$row['HinhAnh']
												,$row['NoiDung']
												,$row['idBaiHat']
												,$row['TenBaiHat']
												,$row['HinhBaiHat']));
	}

	// trả về dữ liệu dạng JSON từ mảng object
	// hàm này có sẵn của PHP, gọi thôi là tự động chuyển Object thành JSON và trả về
	echo json_encode($mangquangcao);

 ?>