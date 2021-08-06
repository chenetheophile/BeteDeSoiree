<?php
$nom=$_POST['nom'];
$temps=$_POST['temps'];
$desc=$_POST['desc'];
$ingr=$_POST['ingr'];
$etape=$_POST['etape'];
$lien=$_POST['lien'];
$prin=$_POST['princ'];
$alcool=$_POST['alcool'];
$fav=$_POST['fav'];
$type=$_POST['type'];

$db_hostname = 'localhost';
$db_username = 'root';
$db_password = 'root';
$db_dbname = 'bdes';
$db_tablename = 'proposition';
$db_conn_str = "mysql:host=" . $db_hostname . ";dbname=" . $db_dbname;
$conn = new PDO($db_conn_str, $db_username, $db_password);
$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
$stmt=$conn->prepare("INSERT INTO `proposition`(`Nom`, `Description`, `Type`, `Ingredient`, `Etape`, `TempsPrepa`, `Alcool`, `Lien`, `Favori`, `Princip`) VALUES (:nom, :descr, :typ, :ing, :etape, :temps, :alc, :lien, :fav, :princi)");
$stmt->bindValue(':nom',$nom,PDO::PARAM_STR);
$stmt->bindValue(':descr',$desc,PDO::PARAM_STR);
$stmt->bindValue(':typ',$type,PDO::PARAM_STR);
$stmt->bindValue(':ing',$ingr,PDO::PARAM_STR);
$stmt->bindValue(':etape',$etape,PDO::PARAM_STR);
$stmt->bindValue(':temps',$temps,PDO::PARAM_STR);
$stmt->bindValue(':alc',$alcool,PDO::PARAM_BOOL);
$stmt->bindValue(':lien',$lien,PDO::PARAM_STR);
$stmt->bindValue(':fav',$fav,PDO::PARAM_BOOL);
$stmt->bindValue(':princi',$prin,PDO::PARAM_STR);
print_r($stmt->execute());


?>