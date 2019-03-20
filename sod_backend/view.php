<?php

require_once 'include/db_functions.php';

$username=$_GET['username'];
$password=$_GET['password'];

$sql_query = "SELECT provider_id, where_located, phone_no, qualification, username, password FROM serviceproviders_tb WHERE username='$username' AND password='$password'";

$records = mysqli_query($connection,$sql_query);

$data = array();

while($row = mysqli_fetch_assoc($records))
{
    $data[] = $row; 
}

echo json_encode($data);

mysqli_close($connection);

//bitbucket.org -- Just like github
?>

