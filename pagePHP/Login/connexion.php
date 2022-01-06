<?php session_start(); ?>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<?php
$db_hostname = 'localhost';
$db_username = 'theo';
$db_password = '0908';
$db_dbname = 'bdes';
$db_tablename = 'login';
$db_conn_str = "mysql:host=" . $db_hostname . ";dbname=" . $db_dbname;

$mdp=$_POST['motdepasse'];
$login=$_POST['login'];

$conn = new PDO($db_conn_str, $db_username, $db_password);
$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

$stmt=$conn->prepare(" SELECT `statut`,COUNT(`UID`) AS nb FROM login WHERE `email`='$login' AND `password`='$mdp' GROUP BY statut");
$stmt->execute();

$result = $stmt->fetch(PDO::FETCH_ASSOC);
if ($result["nb"] == 1) {
    $_SESSION['statut']=$result['statut'];
    $_SESSION['connecte'] = TRUE;
    header("Location: ../Accueil/Accueil.php");
}else{
    header("Location: ../Login/login.php?err=1");
}

?>

</html>
