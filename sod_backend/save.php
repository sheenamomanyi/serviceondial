<?php
 
require_once 'include/db_connection.php';
 global $connection;

// json response array
$response = array("error" => FALSE);
 
 
    // receiving the post params
	$provider_id = $_POST['provider_id'];
	$where_located = $_POST['where_located'];
    $phone_no = $_POST['phone_no'];
    $qualification = $_POST['qualification'];
    $username = $_POST['username'];
    $password = $_POST["password"];

        // manipulate service provider details
$query = "UPDATE serviceproviders_tb SET provider_id='".$provider_id."', where_located='".$where_located."', phone_no = '".$phone_no."', qualification = '".$qualification."', username = '".$username."', password = '".$password."' WHERE username = '".$username."'";

if(mysqli_query($connection, $query))
{
     echo "success";
}
else
{
     echo "failed";
}

mysqli_close($connection);
 
?>