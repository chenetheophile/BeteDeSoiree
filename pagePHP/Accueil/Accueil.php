<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="Accueil.css">
    <title>Document</title>
</head>

<body>
    <table>
        <tr>
            <th>Nom</th>
            <th>Temps</th>
            <th>Description</th>
            <th>Ingredient</th>
            <th>Etape</th>
            <th>Lien</th>
        </tr>
        <?php
        include("../Include/Entete.php");
        $db_hostname = 'localhost';
        $db_username = 'root';
        $db_password = 'root';
        $db_dbname = 'bdes';
        $db_tablename = 'item';
        $db_conn_str = "mysql:host=" . $db_hostname . ";dbname=" . $db_dbname;
        $conn = new PDO($db_conn_str, $db_username, $db_password);
        $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        $stmt = $conn->prepare("SELECT `Nom`, `TempsPrepa`, `Description`, `Ingredient`, `Etape`, `Lien`, `Princip`, `Alcool`, `Favori`, `Type`  FROM $db_tablename WHERE 1");
        $stmt->execute();
        while ($result = $stmt->fetch(PDO::FETCH_ASSOC)) {

        ?>
            <tr>
                <td> <?php print($result['Nom']); ?></td>
                <td> <?php print($result['TempsPrepa']); ?></td>
                <td> <?php print($result['Description']); ?></td>
                <td> <?php print($result['Ingredient']); ?></td>
                <td> <?php print($result['Etape']); ?></td>
                <td> <?php print($result['Lien']);} ?></td>
            </tr>
    </table>
</body>

</html>