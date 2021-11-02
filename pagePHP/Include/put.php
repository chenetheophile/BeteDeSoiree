<?php
print_r($_POST);
$nom=$_POST['nom'];
$temps=$_POST['temps'];
$desc=$_POST['desc'];
$ingr=$_POST['ingr'];
$etape=$_POST['etape'];
$lien=$_POST['lien'];
$prin=$_POST['princ'];
$alcool=$_POST['alcool'];
$type=$_POST['type'];

$db_hostname = 'localhost';
$db_username = 'root';
$db_password = 'root';
$db_dbname = 'bdes';
$db_tablename = $_POST['table'];
$db_conn_str = "mysql:host=" . $db_hostname . ";dbname=" . $db_dbname;
$conn = new PDO($db_conn_str, $db_username, $db_password);
$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
if($db_tablename=="proposition"){
    $stmt=$conn->prepare("INSERT INTO `proposition`(`Nom`, `Description`, `Type`, `Ingredient`, `Etape`, `TempsPrepa`, `Alcool`, `Lien`, `Princip`) VALUES (:nom, :descr, :typ, :ing, :etape, :temps, :alc, :lien, :princi)");
}elseif($db_tablename=="ajout"){
    $stmt=$conn->prepare("INSERT INTO `item`(`Nom`, `Description`, `Type`, `Ingredient`, `Etape`, `TempsPrepa`, `Alcool`, `Lien`, `Princip`) VALUES (:nom, :descr, :typ, :ing, :etape, :temps, :alc, :lien, :princi)");
}
$stmt->bindValue(':nom',$nom,PDO::PARAM_STR);
$stmt->bindValue(':descr',$desc,PDO::PARAM_STR);
$stmt->bindValue(':typ',$type,PDO::PARAM_STR);
$stmt->bindValue(':ing',$ingr,PDO::PARAM_STR);
$stmt->bindValue(':etape',$etape,PDO::PARAM_STR);
$stmt->bindValue(':temps',$temps,PDO::PARAM_STR);
$stmt->bindValue(':alc',$alcool,PDO::PARAM_BOOL);
$stmt->bindValue(':lien',$lien,PDO::PARAM_STR);
$stmt->bindValue(':princi',$prin,PDO::PARAM_STR);
try{
    $stmt->execute();
    if($db_tablename=="ajout"){
        header("Location: ../Accueil/Accueil.php");
    }
}catch(Exception $e){
    print_r($e);
}


?>