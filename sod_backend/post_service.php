<?php
 
require_once 'include/db_functions.php';
 
// json response array
$response = array("error" => FALSE);
 
if (isset($_POST['service_type']) && isset($_POST['provider_id']) && isset($_POST['charge']) && isset($_POST['area']) && isset($_POST['phone_no'])) {
 
    // receiving the post params
	$service_type = $_POST['service_type'];
	$provider_id = $_POST['provider_id'];
    $charge = $_POST['charge'];
    $area = $_POST['area'];
    $phone_no = $_POST['phone_no'];
 
        // post a new service
        $user = postService($service_type, $provider_id, $charge, $area, $phone_no);
        if ($user) {
            // service stored successfully
            $response["error"] = FALSE;
			$response["user"]["id"] = $user["id"];
			$response["user"]["service_type"] = $user["service_type"];
            $response["user"]["provider_id"] = $user["provider_id"];
            $response["user"]["charge"] = $user["charge"];
            $response["user"]["area"] = $user["area"];
            $response["user"]["phone_no"] = $user["phone_no"];
            echo json_encode($response);
        } else {
            // service failed to store
            $response["error"] = TRUE;
            $response["error_msg"] = "Unknown error occurred!";
            echo json_encode($response);
        }
    
} else {
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters missing!";
    echo json_encode($response);
}
?>