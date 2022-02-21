<?php
print_r($_POST);
$nom=$_POST['nom'];
$temps=$_POST['temps'];
$desc=$_POST['desc'];
$ingr=$_POST['ingr'];
$etape=$_POST['etape'];
$lien=$_POST['lien'];
if($_POST['ajoutdb']=='true'){
    $var=array();
    if (strpos($ingr, "@") != FALSE) {
        while (strpos($ingr, "@") != FALSE) {
            array_push($var,substr($ingr, 0, strpos($ingr, "@")));
            $ingr = substr($ingr, strpos($ingr, "@") + 1);
        }
    } else {
        array_push($var,$ingr);
    }
    foreach($var as $ingredient){
        if(chercherTexte($ingredient,"../Include/dictionnaireALcool.txt")){
            $alcool=TRUE;
            break;
        }
    }
    $ingr=$_POST['ingr'];
}else{
    $alcool=$_POST['alcool'];
}

$prin=$_POST['princ'];

$type=$_POST['type'];

$db_hostname = 'localhost';
if (strcmp($_SERVER['SERVER_NAME'], 'localhost')==0 or strcmp($_SERVER['SERVER_NAME'], '192.168.1.14') == 0) {
    $db_username = 'root';
    $db_password = 'root';
} else {
    $db_username = 'theo';
    $db_password = '0908';
}
$db_dbname = 'bdes';
$db_tablename = $_POST['table'];
$db_conn_str = "mysql:host=" . $db_hostname . ";dbname=" . $db_dbname;
$conn = new PDO($db_conn_str, $db_username, $db_password);
$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
if($db_tablename=="proposition"){
    $stmt=$conn->prepare("INSERT INTO `proposition`(`Nom`, `Description`, `Type`, `Ingredient`, `Etape`, `TempsPrepa`, `Alcool`, `Lien`, `Princip`) VALUES (:nom, :descr, :typ, :ing, :etape, :temps, :alc, :lien, :princi)");
}elseif($db_tablename=="ajout"){
    $stmt=$conn->prepare("INSERT INTO `item`(`Nom`, `Description`, `Type`, `Ingredient`, `Etape`, `TempsPrepa`, `Alcool`, `Lien`, `Princip`) VALUES (:nom, :descr, :typ, :ing, :etape, :temps, :alc, :lien, :princi)");
}elseif($db_tablename=="ajoutdb"){
    $stmt=$conn->prepare("INSERT INTO `proposition`(`Nom`, `Description`, `Type`, `Ingredient`, `Etape`, `TempsPrepa`, `Alcool`, `Lien`, `Princip`) VALUES (:nom, :descr, :typ, :ing, :etape, :temps, :alc, :lien, :princi)");
}else{
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
    if($db_tablename=="ajoutdb"){
        header("Location: ../BDD/ajoutdb.php");
    }
}catch(Exception $e){
    print_r($e);
}

function chercherTexte($recherche,$nomfichier){
   $contents = file_get_contents($nomfichier);
   $pattern = preg_quote($recherche, '/');

    $pattern = "/^.*$pattern.*\$/m";

    if(preg_match_all($pattern, $contents, $matches)){
    return TRUE;
    }
    else{
    return FALSE;
    }
}
