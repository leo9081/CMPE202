<?php

use \Jacwright\RestServer\RestException;
include'db_connection.php';

class TestController
{
    /**
     * Returns a JSON string object to the browser when hitting the root of the domain
     *
     * @url GET /
     */
    public function test()
    {
      $myfile = fopen("contactsinfo.txt", "r") or die("Unable to open file!");
      echo fread($myfile,filesize("testing.txt"));
      fclose($myfile);
      return;
    }


    /**
     * Returns a JSON string object to the browser when hitting the root of the domain
     *
     * @url GET /contacts
     */
    public function contacts()
    {
      $myfile = fopen("testing.txt", "r") or die("Unable to open file!");
      echo fread($myfile,filesize("testing.txt"));
      fclose($myfile);
      return;
    }

    /**
     * Logs in a user with the given username and password POSTed. Though true
     * REST doesn't believe in sessions, it is often desirable for an AJAX server.
     *
     * @url POST /login
     */
    public function login($data)
    {
        $username = $data->username;
        $password = $data->pwd; 
	

	$conn = OpenCon();
      	$query = "SELECT * from UserInfo where username = '" . $username . "'AND password = '" . $password . "'" ;
	$result = $conn->query($query);
	$userObj = array();
	
      	if ($result->num_rows > 0) {
		$userObj = mysqli_fetch_object($result);
      	} else {
        	return "0 results";
      	}
	mysqli_free_result($result);

	$token = hexdec(bin2hex(openssl_random_pseudo_bytes(4)));
	$userObj -> userToken = strval($token);
	
	$query = "Update UserInfo set userToken = '" . $token . "' where userId =  '" . $userObj -> userId . "'" ;

	$result = $conn->query($query);
	
        return $userObj;
    }

    /**
     * Logs in a user with the given username and password POSTed. Though true
     * REST doesn't believe in sessions, it is often desirable for an AJAX server.
     *
     * @url POST /logout
     */
    public function logout($data)
    {
        $userId = $data -> userId;
        $userToken = $data -> token;

        $conn = OpenCon();
        $query = "SELECT * from UserInfo where userId = '" . $userId . "'AND userToken = '" . $userToken . "'" ;
        $result = $conn->query($query);
        $userObj = array();

        if ($result->num_rows > 0) {
        	$query = "Update UserInfo set userToken = '' where userId =  '" . $userObj -> userId . "'" ;
        	$result = $conn->query($query);
		return 0;
	} else {
		return 1;
	}

    }

    


    /**
     * store information in cookieData
     *
     * @url POST /writeCookie
     */
    public function writeCookie($data){
	      $cookie_name = $data -> cookieName;
        $cookie_value = $data -> cookieData;

        $boolean = false;

        if(isset($_COOKIE[$cookie_name])){
          $_COOKIE[$cookie_name]++;
          $boolean = setcookie($cookie_name, $_COOKIE[$cookie_name], time()+60*60*24);
          echo $_COOKIE[$cookie_name];
        }else{
          $boolean = setcookie($cookie_name, 1, time()+60*60*24);
          echo $cookie_name;
        }

    	return $boolean;
    }

    /**
     * Gets allusers
     *
     * @url GET /getallusers
     */
    public function getallusers()
    {
      $conn = OpenCon();
      $query = "SELECT * from UserInfo" ;
      $result = $conn->query($query);
      $rows = array();

      if ($result->num_rows > 0) {
        while($r = $result->fetch_assoc()) {
          $rows[] = $r;
        }
      } else {
        echo "0 results";
      }

      $ch = curl_init("http://www.iaskhu.com/get_user_list.php");
      curl_setopt($ch,CURLOPT_RETURNTRANSFER,1);
      $output1 = curl_exec($ch);
      curl_close($ch);
      $output1 = json_decode($output1);
      for($i = 0; $i < count($output1); $i++){
        $rows[] = $output1[$i];
      }

      $ch = curl_init("http://guansiling.com/Users.php");
      curl_setopt($ch,CURLOPT_RETURNTRANSFER,1);
      $output2 = curl_exec($ch);
      curl_close($ch);
      $data = json_decode($output2,true);
      for($i = 0; $i < count($data['result']); $i++){
        $rows[] = $data['result'][$i];
      }

      return $rows;
    }



    /**
     * Saves a user to the database
     *
     * @url POST /createuser
     */
    public function createUser($data)
    {
        // ... validate $data properties such as $data->username, $data->firstName, etc.
        // $data->id = $id;
        // $user = User::saveUser($data); // saving the user to the database
        //var_dump($data);
	$username = $data -> username;
        $password = $data -> pwd;

	$firstName = $data -> firstName;
        $lastName = $data -> lastName;
        $email = $data -> email;
	$home_address = $data -> home_address;
	$home_phone = $data -> home_phone;
        $cellPhone = $data -> cellphone;

	$conn = OpenCon();
	$query = "INSERT INTO UserInfo (username,password,firstName,lastName,email,homeAddress,homePhone,cellPhone) VALUES('" . $username ."','" .$password ."','" . $firstName ."','" . $lastName ."','" . $email ."','" . $home_addres ."','" . $home_phone ."','" . $cellPhone . "')";
	$result = $conn->query($query);
	$returnVal = 'Insert Failed!';
	if($result) $returnVal = 'Succeed!';
	$conn->close();
        return $returnVal; // returning the updated or newly created user object
    }

    /**
     * Gets user list
     *
     * @url GET /users
     */
    public function listUsers($query)
    {
	$firstName = $query['firstName'];
	$lastName = $query['lastName'];
	$email = $query['email'];
	$cellPhone = $query['cellPhone'];

	if($firstName or $lastName or $email or $cellPhone){
		$apd = '';
		if($firstName){
			$apd .= "firstName ='". $firstName . "'";
		}
		if($lastName){
			if($apd!=''){
				$apd .= ' AND ';
			}
			$apd .= "lastName ='". $lastName . "'";
		}
		if($email){
			if($apd!=''){
                                $apd .= ' AND ';
                        }
			$apd .= "email ='" . $email . "'";
		}
		if($cellPhone){
			if($apd!=''){
                                $apd .= ' AND ';
                        }
			$apd .= "cellPhone ='" . $cellPhone . "'";
		}
		$conn = OpenCon();
                # this may not get the result
                $query = "SELECT * from UserInfo where ($apd)" ;
                $result = $conn->query($query);
                $row = mysqli_fetch_assoc($result);
                if($row){
                        return $row;
                }else{
                        echo 'no result';
                        return;
                }
	}else{
		echo 'Please enter at least one section.';
		return;
	}

    }


    /**
     * Gets user list
     *
     * @url GET /listUsers
     */
    public function userList()
    {
      $conn = OpenCon();
      $query = "SELECT * from UserInfo" ;
      $result = $conn->query($query);
      $rows = array();

      if ($result->num_rows > 0) {
        while($r = $result->fetch_assoc()) {
          $rows[] = $r;
        }
      } else {
        echo "0 results";
      }
      return $rows;
    }

    /**
     * add a new card
     *
     * @url POST /addNewCard
     */
    public function addNewCard($data){
	//get UserId UserToken
	$userId = $data->userId;
	$userToken = $data->userToken;

	$conn = OpenCon();
      	$query = "SELECT * from UserInfo where userId = '" . $userId . "' AND userToken = '" . $userToken . "'" ;
      	$result = $conn->query($query);

      	if ($result->num_rows > 0) {
		$query = "SELECT * FROM Cards where cardHolderId = '" . $userId . "' AND isActive = 1";
		$result = $conn->query($query);
		if($result->num_rows > 0){
			//disable current card;
			$cardObj = mysqli_fetch_object($result);
			$query = "UPDATE Cards set isActive = 0 where cardId = '" . $cardObj->cardId . "'";
			$conn->query($query);
		}
		
		$cardNum = $data->cardNum;
		$cardCode = $data->cardCode;
		$balance = 20;
	
		$query = "INSERT INTO Cards (cardNum,cardCode,cardHolderId,balance,isActive) VALUES('" . $cardNum ."','" .$cardCode ."','" . $userId ."','" . $balance ."', 1)";
		$result = $conn->query($query);
	
		$query = "SELECT * FROM Cards where cardHolderId = '" . $userId . "' AND isActive = 1";
                $result = $conn->query($query);
		$cardObj = mysqli_fetch_object($result);	
		return $cardObj;
	}else{
		return 1;
	}
    }

    /**
     * make a payment
     *
     * @url POST /makePayment
     */
    public function makePayment($data){
	$userId = $data->userId;
        $userToken = $data->userToken;

        $conn = OpenCon();
        $query = "SELECT * from UserInfo where userId = '" . $userId . "' AND userToken = '" . $userToken . "'" ;
        $result = $conn->query($query);

        if ($result->num_rows > 0) {
		$query = "SELECT * FROM Cards where cardHolderId = '" . $userId . "' AND isActive = 1";
                $result = $conn->query($query);
                if($result->num_rows > 0){
                        $cardObj = mysqli_fetch_object($result);
			
			$query = "INSERT INTO Payments (userId, cardId,cardNum,cost) VALUES('" . $userId . "','" . $cardObj->cardId . "','" . $cardObj->cardNum . "', 1.5)";
                        $conn->query($query);			

			$balance = $cardObj->balance;
			if($balance <1.5){
				$balanceObj->balance = $balance;
				return $balanceObj;
			}else {
				$balance = $cardObj->balance - 1.5;
                        	$query = "UPDATE Cards set balance = '".$balance ."'  where cardId = '" . $cardObj->cardId . "'";
                        	$conn->query($query);
				$balanceObj->balance = $balance;
				return $balanceObj;
			}
                }else return 1;
	}else return 1;
    }
	
    /**
     * get a payment History
     *
     * @url GET /getPaymentHistory/$id/$token
     */
    public function getPaymentHistory($id, $token){
	$userId = $id;
	$userToken = $token;
        $conn = OpenCon();
        $query = "SELECT * from UserInfo where userId = '" . $userId . "' AND userToken = '" . $userToken . "'" ;
	$result = $conn->query($query);
	if ($result->num_rows > 0) {
		$query = "SELECT * from Payments where userId = '" . $userId . "'";
		$result = $conn->query($query);
		$rows = array();
      
      		if ($result->num_rows > 0) {
        		while($r = $result->fetch_assoc()) {
          			$rows[] = $r;
        		}
      		} else {
        		echo "0 results";
      		}
      		return $rows;	
	}else return;
    }

    /**
     * get User related tables
     *
     * @url GET /getUserRelatedInfo/$id//$token
     */
    public function getUserRelatedInfo($id,$token){
        $userId = $id;
        $userToken = $token;
        	
    	$cardObj = getCurrentUseCard($userId,$userToken);
	
    }



    /**
     * get current User used card
     *
     * @url GET /getCurrentUseCard/$id/$token
     */
    public function getCurrentUseCard($id,$token){
        $userId = $id;
	$userToken = $token;
        $conn = OpenCon();
        
	$query = "SELECT * from UserInfo where userId = '" . $userId . "' AND userToken = '" . $userToken . "'" ;
	$result = $conn->query($query);
        if ($result->num_rows > 0) {
        	$query = "SELECT * FROM Cards where cardHolderId = '" . $userId . "' AND isActive = 1";
	         $result = $conn->query($query);
                if($result->num_rows > 0){
                        $cardObj = mysqli_fetch_object($result);
			return $cardObj;
		}
	}

	return "no card find";
    }

    /**
     * Get Charts
     *
     * @url GET /charts
     * @url GET /charts/$id
     * @url GET /charts/$id/$date
     * @url GET /charts/$id/$date/$interval/
     * @url GET /charts/$id/$date/$interval/$interval_months
     */
    public function getCharts($id=null, $date=null, $interval = 30, $interval_months = 12)
    {
        echo "$id, $date, $interval, $interval_months";
    }

    /**
     * Throws an error
     *
     * @url GET /error
     */
    public function throwError() {
        throw new RestException(401, "Empty password not allowed");
    }


}
