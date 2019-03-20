
<?php
require_once 'include/db_functions.php';

$sql = "SELECT * FROM services_tb ORDER BY entry_time DESC";
$r = mysqli_query($connection,$sql);
$result = array();

while($res = mysqli_fetch_array($r)){
array_push($result,array(
"service_type"=>$res['service_type'],
"provider_id"=>$res['provider_id'],
"charge"=>$res['charge'],
"area"=>$res['area'],
"phone_no"=>$res['phone_no'])
);
}

echo json_encode(array("result"=>$result));
mysqli_close($connection);
?>

<!-- INSERT INTO `services_tb` (`id`, `service_type`, `provider_id`, `charge`, `area`, `entry_time`) VALUES (NULL, 'Electrical Wiring', 'Collins \'The Engineer\'', '4k per room/unit. Price negotiable.', 'Kisumu county', CURRENT_TIMESTAMP), (NULL, 'Network Installation', 'John The Computer Guy', '7k for every 10 workstation, price negotiable', 'Mombasa, Kilifi, Kwale County', CURRENT_TIMESTAMP), (NULL, 'Building & Construction', 'Collins \'The Engineer\'', 'Friendly charges', 'Kisumu Area', CURRENT_TIMESTAMP), (NULL, 'Computer Repair', 'John The Computer Guy', 'Pocket friendly charges', 'Mombasa CBD', CURRENT_TIMESTAMP), (NULL, 'Motor Mechanic', 'Collins \'The Engineer\'', 'Friendly charges and discount for returning customers', 'Kisumu City', CURRENT_TIMESTAMP), (NULL, 'Plumbing', 'John The Plumber', 'Pocket friendly charges', 'Nairobi CBD', CURRENT_TIMESTAMP); -->

<!-- INSERT INTO `androidosnames` (`id`, `AndroidNames`, `ImagePath`) VALUES (NULL, 'Joy Kamali', 'http://192.168.43.58/sod_backend/images/joy_kamali.jpg'), (NULL, 'Sivi Bag', 'http://192.168.43.58/sod_backend/images/bag.jpg'); -->
