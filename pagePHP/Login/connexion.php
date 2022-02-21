<?php session_start();
$db_hostname = 'localhost';
if (strcmp($_SERVER['SERVER_NAME'], 'localhost') == 0 or strcmp($_SERVER['SERVER_NAME'], '192.168.1.14') == 0 ) {
    $db_username = 'root';
    $db_password = 'root';
} else {
    $db_username = 'theo';
    $db_password = '0908';
}
$db_dbname = 'bdes';
$db_tablename = 'login';
$db_conn_str = "mysql:host=" . $db_hostname . ";dbname=" . $db_dbname;

if (isset($_POST['application'])) {
    $type = true;
} else {
    $type = false;
}
$mdp = $_POST['motdepasse'];
$login = $_POST['login'];

$conn = new PDO($db_conn_str, $db_username, $db_password);
$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

$stmt = $conn->prepare(" SELECT `statut`,COUNT(`UID`) AS nb FROM login WHERE `email`='$login' AND `password`='$mdp' GROUP BY statut");
$stmt->execute();

$result = $stmt->fetch(PDO::FETCH_ASSOC);

if ($type == false) {
    if ($result["nb"] == 1) {
        $_SESSION['statut'] = $result['statut'];
        $_SESSION['connecte'] = TRUE;
        header("Location: ../Accueil/accueil.php");
    } else {
        header("Location: ../Login/login.php?err=1");
    }
} else {
    if($result['nb']==1){
        echo $result['nb'];
    }else{
        $stmt = $conn->prepare(" INSERT INTO `login`(`email`, `password`) VALUES (:email,:password)");
        $stmt->bindValue(":email",$login);
        $stmt->bindValue(":password",$mdp); 
        $stmt->execute();
        echo 0;
    }
    
}
?>
