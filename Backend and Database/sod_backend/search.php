<?php
require_once 'include/db_functions.php';
 
// json response array
$response = array("error" => FALSE);
 
if (isset($_POST['service_type']) && isset($_POST['area'])) {
 
    // receiving the post params
    $service_type = $_POST['service_type'];
    $area = $_POST['area'];
 
    // get the search params
    $services = getServicesByServiceIdAndLocation($service_type, $area);
 
    if ($services != false) {
        // services
        $response["error"] = FALSE;
		$response["services"]["service_type"] = $services["service_type"];
        $response["services"]["area"] = $services["area"];  
		
        echo json_encode($response);
    } else {
        // No service found
        $response["error"] = TRUE;
        $response["error_msg"] = "Your Search Didn't Match any Services. Try a different Search.";
        echo json_encode($response);
    }
} else {
    //required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters missing :(!";
    echo json_encode($response);
}
?>