<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="recette.css">
    <link rel="shortcut icon" href="../Include/Image/logo_appli.png" type="image/x-icon">
    <title>Recette</title>
</head>

<body>
    <?php
    include("../Include/Entete.php");
    ?>
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
    $stmt = $conn->prepare("SELECT `Nom`, `TempsPrepa`, `Description`, `Ingredient`, `Etape`, `Lien`, `Princip`, `Alcool` FROM $db_tablename WHERE Type='Recette'");
    $stmt->execute();
    ?>
    <table class="tableau">
        <tr>
            <th><h1>Nom</h1></th>
            <th><h1>Temps</h1></th>
            <th><h1>Description</h1></th>
            <th><h1>Ingredient</h1></th>
            <th><h1>Etape</h1></th>
            <th><h1>Image</h1></th>
        </tr>
        <?php
        while ($result = $stmt->fetch(PDO::FETCH_ASSOC)) {
        ?>
            <tr>
                <td> <?php print("<div class='texteCase'>".$result['Nom']."</div>"); ?></td>
                <td class="case"> <?php print("<div class='texteCase'>".$result['TempsPrepa']."</div>"); ?></td>
                <td class="case"> <?php print("<div class='texteCase'>".$result['Description']."</div>"); ?></td>
                <td class="case"> <?php print(afficherCase($result['Ingredient'])); ?></td>
                <td class="case"> <?php print(afficherCase($result['Etape'])); ?></td>
                <td class="case"><img class="imglien" src="<?php print($result['Lien']); ?>" alt=""></td>
            </tr>
    </table>
<?php }
        $stmt = $conn->prepare("SELECT `Nom`, `TempsPrepa`, `Description`, `Ingredient`, `Etape`, `Lien`, `Princip`, `Alcool` FROM $db_tablename WHERE Type='Cocktail'");
        $stmt->execute();
        function afficherCase($string)
        {
            $var = "<table class='souscase'>";
            if (strpos($string, "@") != FALSE) {
                while (strpos($string, "@") != FALSE) {
                    $var .= "<tr ><td ><p  class='texteCase'>";
                    $var .= substr($string, 0, strpos($string, "@"));
                    $string = substr($string, strpos($string, "@") + 1);
                    $var .= "</p></tr></td>";
                }
            } else {
                $var .= $string;
            }
            $var .= "</table>";
            return $var;
        }
?>


</body>

</html>