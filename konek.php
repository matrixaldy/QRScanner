<?php
 define('HOST','localhost');
 define('USER','root');
 define('PASS','');
 define('DB','flight');
 
 $con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');
 ?>