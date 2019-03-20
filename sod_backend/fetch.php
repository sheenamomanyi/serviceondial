<?php

$host='127.0.0.1';
$username='root';
$pwd='';
$db="serviceondial_db";

$con=mysqli_connect($host,$username,$pwd,$db) or die('Unable to connect');

if(mysqli_connect_error($con))
{
  echo "Failed to Connect to Database ".mysqli_connect_error();
}

// receiving the post params
    $service_type = $_POST['service_type'];
    $area = $_POST['area'];

    // $service_type = "Taxi";
    // $area = "Mombasa";

$sql="SELECT * FROM services_tb WHERE service_type = '{$service_type}' AND area = '{$area}' ORDER BY entry_time DESC";

$query=mysqli_query($con,$sql);
$data = array();

if($query)
{
    while($row=mysqli_fetch_array($query))
  {
    // $data=$row;
    array_push($data,array(
    	"service_type"=>$row['service_type'],
    	"provider_id"=>$row['provider_id'],
"charge"=>$row['charge'],
"area"=>$row['area'],
"phone_no"=>$row['phone_no'])
);
  }

    print(json_encode($data));
    //echo json_encode($data);

}else
{
  echo('No Services Found ');
}
mysqli_close($con);

// while($res = mysqli_fetch_array($query)){
// array_push($data,array(
// "service_type"=>$res['service_type'],
// "provider_id"=>$res['provider_id'],
// "charge"=>$res['charge'],
// "area"=>$res['area'],
// "phone_no"=>$res['phone_no'])
// );
// }

// echo json_encode($data);
?>