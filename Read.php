<?php 
	//Import File Koneksi Database
	require_once('koneksi.php');
	
	//Membuat SQL Query
	$sql = "SELECT * FROM tb_penumpang";
	
	//Mendapatkan Hasil
	$r = mysqli_query($con,$sql);
	
	//Membuat Array Kosong 
	$result = array();
	
	while($row = mysqli_fetch_array($r)){
		
		$date = strtotime($row['boarding_time']);
		array_push($result,array(
		"id"=>$row['id_penumpang'],
		"fname"=>$row['nama_depan'],
		"lname" =>$row['nama_belakang'],
		"fn" =>$row['flight_number'],
		"gate" =>$row['gate'],
		"from" =>$row['dari'],
		"to" =>$row['tujuan'],
		"seat" =>$row['seat'],
		"jam" => date('H:i', $date),
		"tgl" => date('d-m', $date)
		));
	}
	
	//Menampilkan Array dalam Format JSON
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);
?>