<?php
	require "connect.php";

	class TheLoai{
		function TheLoai($idtheloai,$idkeychude,$tentheloai,$hinhtheloai){
			$this->IdTheLoai= $idtheloai;
			$this->IdKeyChuDe = $idkeychude;
			$this->TenTheLoai = $tentheloai;
			$this->HinhTheLoai = $hinhtheloai;
		}
	}

	class ChuDe{
		function ChuDe($idchude,$tenchude,$hinhchude){
			$this->IdChuDe = $idchude;
			$this->TenChuDe = $tenchude;
			$this->HinhChuDe = $hinhchude;
		}
	}

	$arraytheloai = array();
	$arraychude = array();


	$querytheloai = "SELECT DISTINCT * FROM theloai ORDER BY rand(". date("Ymd"). ") LIMIT 4";
	$datatheloai = mysqli_query($myConnect,$querytheloai);
	while ($row = mysqli_fetch_assoc($datatheloai)) {
		array_push($arraytheloai, new TheLoai($row['idTheLoai']
											 ,$row['idChuDe']
											 ,$row['TenTheLoai']
											 ,$row['HinhTheLoai']));
	}




	$querychude = "SELECT DISTINCT * FROM chude ORDER BY rand(". date("Ymd"). ") LIMIT 4";
	$datachude = mysqli_query($myConnect,$querychude);
	while ($row = mysqli_fetch_assoc($datachude)) {
		array_push($arraychude, new ChuDe($row['idChuDe']
											 ,$row['TenChuDe']
											 ,$row['HinhChuDe']));
	}

	// Tạo ra 1 mảng gồm 2 phần tử.
	// 1 là mảng có tên là MangTheLoai
	// 1 là mảng có tên là MangChuDe
	$arraychudetheloai = array('MangTheLoai'=>$arraytheloai,'MangChuDe'=>$arraychude);
	echo json_encode($arraychudetheloai);
	
	
?>
<!-- => JSON trả về sẽ có dạng: -->
<!-- {
  "MangTheLoai": [
    {
      "IdTheLoai": "5",
      "IdKeyChuDe": "2",
      "TenTheLoai": "Những bài hát hay về mưa",
      "HinhTheLoai": "https://dquand91.000webhostapp.com/HinhAnhFolder/theloai/nhungngaymua/nhungbaihathaynhatvemua.jpg"
    },
    {
      ...
    }
  ],
  "MangChuDe": [
    {
      "IdChuDe": "5",
      "TenChuDe": "Nhạc cho sàn nhảy",
      "HinhChuDe": "https://dquand91.000webhostapp.com/HinhAnhFolder/chude/edm.png"
    },
    {
      ...
    },    
  ]
} -->


