<?php require_once("include/db_connection.php");?>
<?php

	function storeUser($provider_id, $where_located, $phone_no, $qualification, $username, $password){
		global $connection;
		
		$query = "INSERT INTO serviceproviders_tb(";
		$query .= "provider_id, where_located, phone_no, qualification, username, password) ";
		$query .= "VALUES('{$provider_id}', '{$where_located}', '{$phone_no}', '{$qualification}', '{$username}', '{$password}')";

		$result = mysqli_query($connection, $query);

		if($result){
			$user = "SELECT * FROM serviceproviders_tb WHERE username = '{$username}' AND password = '{$password}'";
			$res = mysqli_query($connection, $user);

			while ($user = mysqli_fetch_assoc($res)){
				return $user;
			}
		}else{
				return false;
			}

	}


	function getUserByUsernameAndPassword($username, $password){
		global $connection;
		$query = "SELECT * from serviceproviders_tb where username = '{$username}' and password = '{$password}'";
	
		$user = mysqli_query($connection, $query);
		
		if($user){
			while ($res = mysqli_fetch_assoc($user)){
				return $res;
			}
		}
		else{
			return false;
		}
	}


	function usernameExists($username){
		global $connection;
		$query = "SELECT username from serviceproviders_tb where username = '{$username}'";

		$result = mysqli_query($connection, $query);

		if(mysqli_num_rows($result) > 0){
			return true;
		}else{
			return false;
		}
	}

	function getServicesByServiceIdAndLocation($service_type, $area){
		global $connection;
		$query = "SELECT * from services_tb where service_type = '{$service_type}' and area = '{$area}' ORDER BY entry_time DESC";
	
		$services = mysqli_query($connection, $query);
		
		if($services){
			while ($res = mysqli_fetch_assoc($services)){
				return $res;
			}
		}
		else{
			return false;
		}
	}

	function saveInfo($provider_id, $where_located, $phone_no, $qualification, $username, $password){
		global $connection;
		
		$query = "UPDATE serviceproviders_tb SET provider_id = '".$provider_id."', where_located = '".$where_located."', phone_no = '".$phone_no."', qualification = '".$qualification."', username = '".$username."', password = '".$password."' WHERE username = '".$username."'";

		$result = mysqli_query($connection, $query);

		if($result){
			return true;
		}else{
				return false;
			}

	}
?>