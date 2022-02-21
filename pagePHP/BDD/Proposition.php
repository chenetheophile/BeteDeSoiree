<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="proposition.css">
    <link rel="shortcut icon" href="../Include/Image/logo_appli.png" type="image/x-icon">
    <title>Proposition</title>
</head>
<body>
<?php
include("../Include/Entete.php");
    $db_hostname = 'localhost';
    if (strcmp($_SERVER['SERVER_NAME'], 'localhost')==0 or strcmp($_SERVER['SERVER_NAME'], '192.168.1.14') == 0) {
        $db_username = 'root';
        $db_password = 'root';
    } else {
        $db_username = 'theo';
        $db_password = '0908';
    }
    $db_dbname = 'bdes';
    $db_tablename = 'proposition';
    $db_conn_str = "mysql:host=" . $db_hostname . ";dbname=" . $db_dbname;
    $conn = new PDO($db_conn_str, $db_username, $db_password);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    ?>

        <table class="tableau">
            <tr>
                <th><h1>Nom</h1></th>
                <th><h1>Temps</h1></th>
                <th><h1>Description</h1></th>
                <th><h1>Ingredient</h1></th>
                <th><h1>Etape</h1></th>
                <th><h1>Lien</h1></th>
                <th><h1>Image</h1></th>
                <th><h1>Validation</h1></th>
                <th><h1>Suppression</h1></th>
            </tr>
            <?php
            
            $stmt = $conn->prepare("SELECT `ID`,`Nom`, `TempsPrepa`, `Description`, `Ingredient`, `Etape`, `Lien`, `Princip`, `Alcool`, `Type`  FROM $db_tablename WHERE 1");
            $stmt->execute();
            while ($result = $stmt->fetch(PDO::FETCH_ASSOC)) {

            ?>
                <form action="../BDD/suppressiondb.php" method="POST">
                    <input type="hidden" name="ID" value="<?php print($result['ID']); ?>">
                    <tr>
                        <td class="case"> <?php print($result['Nom']); ?></td>
                        <td class="case"> <?php print($result['TempsPrepa']); ?></td>
                        <td class="case"> <?php print($result['Description']); ?></td>
                        <td class="case"> <?php print(afficherCase($result['Ingredient'])); ?></td>
                        <td class="case"> <?php print(afficherCase($result['Etape'])); ?></td>
                        <td class="case"> <input class="txtlien" type="text" name="lien" value="<?php print($result['Lien']); ?>"></input></td>
                        <td class="case"><img class="imglien" src="<?php print($result['Lien']); ?>" alt=""></td>
                        <td class="bouton"><button type="submit" name="bouton" value="valider"><img src="../Include/Image/valider.png" alt=""></button></td>
                        <td class="bouton"><button type="submit" name="bouton" value="supprimer"><img src="../Include/Image/refus.png" alt=""></button></td>
                    </tr>
                </form>
            <?php } ?>
        </table>
</body>

</html>
<?php
function afficherCase($string)
{
    $var = "<table>";
    if (strpos($string, "@") != FALSE) {
        while (strpos($string, "@") != FALSE) {
            $var .= "<tr><td>";
            $var .= substr($string, 0, strpos($string, "@"));
            $string = substr($string, strpos($string, "@") + 1);
            $var .= "</tr></td>";
        }
    } else {
        $var.=$string;
    }
    $var .= "</table>";
    return $var;
}
?>
</body>
</html>