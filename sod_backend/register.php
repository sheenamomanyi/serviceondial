<?php
 
require_once 'include/db_functions.php';
 
// json response array
$response = array("error" => FALSE);
 
if (isset($_POST['provider_id']) && isset($_POST['where_located']) && isset($_POST['phone_no']) && isset($_POST['qualification']) && isset($_POST['username']) && isset($_POST['password'])) {
 
    // receiving the post params
	$provider_id = $_POST['provider_id'];
	$where_located = $_POST['where_located'];
    $phone_no = $_POST['phone_no'];
    $qualification = $_POST['qualification'];
    $username = $_POST['username'];
    $password = $_POST["password"];
 
    // check if service provider already exists with the same username
    if(usernameExists($username)){
		// username already exists
        $response["error"] = TRUE;
        $response["error_msg"] = "Username already exists with " . $username;
        echo json_encode($response);
	}else {
        // create a new service provider
        $user = storeUser($provider_id, $where_located, $phone_no, $qualification, $username, $password);
        if ($user) {
            // service provider stored successfully
            $response["error"] = FALSE;
			$response["user"]["id"] = $user["id"];
			$response["user"]["provider_id"] = $user["provider_id"];
            $response["user"]["where_located"] = $user["where_located"];
            $response["user"]["phone_no"] = $user["phone_no"];
            $response["user"]["qualification"] = $user["qualification"];
            $response["user"]["username"] = $user["username"];
            $response["user"]["password"] = $user["password"];
            echo json_encode($response);
        } else {
            // service providers failed to store
            $response["error"] = TRUE;
            $response["error_msg"] = "Unknown error occurred!";
            echo json_encode($response);
        }
    }
} else {
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters missing!";
    echo json_encode($response);
}
?>