<?php
$db_hostname = 'localhost';
if (strcmp($_SERVER['SERVER_NAME'], 'localhost')==0 or strcmp($_SERVER['SERVER_NAME'], '192.168.1.14') == 0) {
    $db_username = 'root';
    $db_password = 'root';
} else {
    $db_username = 'theo';
    $db_password = '0908';
}
$db_dbname = 'bdes';
$db_tablename = 'item';
$db_conn_str = "mysql:host=" . $db_hostname . ";dbname=" . $db_dbname;
$conn = new PDO($db_conn_str, $db_username, $db_password);
$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
$stmt=$conn->prepare("SELECT `Nom`, `TempsPrepa`, `Description`, `Ingredient`, `Etape`, `Lien`, `Princip`, `Alcool`, `Type`  FROM $db_tablename WHERE 1");
$stmt->execute();
while($result=$stmt->fetch(PDO::FETCH_ASSOC)){
    echo (json_encode($result,JSON_UNESCAPED_UNICODE));
    echo "%";
}

?>
