<?php
$db_hostname = 'localhost';
$db_username = 'root';
$db_password = 'root';
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