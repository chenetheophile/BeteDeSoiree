<?php session_start();
if ($_SESSION["connecte"] == FALSE) {
    session_unset();
};
?>
<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="Accueil.css">
    <title>Document</title>
</head>

<body>
    <?php include("../Include/Entete.php");
    $db_hostname = 'localhost';
    $db_username = 'root';
    $db_password = 'root';
    $db_dbname = 'bdes';
    $db_tablename = 'item';
    $db_conn_str = "mysql:host=" . $db_hostname . ";dbname=" . $db_dbname;
    $conn = new PDO($db_conn_str, $db_username, $db_password);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $stmt = $conn->prepare("SELECT `Nom`, `TempsPrepa`, `Description`, `Ingredient`, `Etape`, `Lien`, `Princip`, `Alcool` FROM $db_tablename WHERE Type='Recette'");
    $stmt->execute();
    print("Recette:");
    ?>
    <table class="tableau">
        <tr>
            <th>Nom</th>
            <th>Temps</th>
            <th>Description</th>
            <th>Ingredient</th>
            <th>Etape</th>
            <th>Image</th>
        </tr>
        <?php
        while ($result = $stmt->fetch(PDO::FETCH_ASSOC)) {
        ?>
            <tr>
                <td class="case"> <?php print($result['Nom']); ?></td>
                <td class="case"> <?php print($result['TempsPrepa']); ?></td>
                <td class="case"> <?php print($result['Description']); ?></td>
                <td class="case"> <?php print(afficherCase($result['Ingredient'])); ?></td>
                <td class="case"> <?php print(afficherCase($result['Etape'])); ?></td>
                <td class="case"><img class="imglien" src="<?php print($result['Lien']); ?>" alt=""></td>
            </tr>
    </table>
<?php }
        $stmt = $conn->prepare("SELECT `Nom`, `TempsPrepa`, `Description`, `Ingredient`, `Etape`, `Lien`, `Princip`, `Alcool` FROM $db_tablename WHERE Type='Cocktail'");
        $stmt->execute();
        print("Cocktail:");
?>
<table class="tableau">
    <tr>
        <th>Nom</th>
        <th>Temps</th>
        <th>Description</th>
        <th>Ingredient</th>
        <th>Etape</th>
        <th>Image</th>
    </tr>
    <?php
    while ($result = $stmt->fetch(PDO::FETCH_ASSOC)) {
    ?>
        <tr>
            <td class="case"> <?php print($result['Nom']); ?></td>
            <td class="case"> <?php print($result['TempsPrepa']); ?></td>
            <td class="case"> <?php print($result['Description']); ?></td>
            <td class="case"> <?php print(afficherCase($result['Ingredient'])); ?></td>
            <td class="case"> <?php print(afficherCase($result['Etape'])); ?></td>
            <td class="case"><img class="imglien" src="<?php print($result['Lien']); ?>" alt=""></td>
        </tr>
        </form>
    <?php }
    if ($_SESSION['statut'] == "ADMIN") {
    ?>

        <table class="tableau">
            <tr>
                <th>Nom</th>
                <th>Temps</th>
                <th>Description</th>
                <th>Ingredient</th>
                <th>Etape</th>
                <th>Lien</th>
                <th>Image</th>
                <th>Validation</th>
                <th>Suppression</th>
            </tr>
            <?php
            $db_tablename = 'proposition';
            $stmt = $conn->prepare("SELECT `ID`,`Nom`, `TempsPrepa`, `Description`, `Ingredient`, `Etape`, `Lien`, `Princip`, `Alcool`, `Type`  FROM $db_tablename WHERE 1");
            $stmt->execute();
            print("Proposition:");
            while ($result = $stmt->fetch(PDO::FETCH_ASSOC)) {

            ?>
                <form action="../Accueil/suppressiondb.php" method="POST">
                    <input type="hidden" name="ID" value="<?php print($result['ID']); ?>">
                    <tr>
                        <td class="case"> <?php print($result['Nom']); ?></td>
                        <td class="case"> <?php print($result['TempsPrepa']); ?></td>
                        <td class="case"> <?php print($result['Description']); ?></td>
                        <td class="case"> <?php print(afficherCase($result['Ingredient'])); ?></td>
                        <td class="case"> <?php print(afficherCase($result['Etape'])); ?></td>
                        <td class="case"> <input class="txtlien" type="text" name="lien" value="<?php print($result['Lien']); ?>"></input></td>
                        <td class="case"><img class="imglien" src="<?php print($result['Lien']); ?>" alt=""></td>
                        <td class="valider"><button type="submit" name="bouton" value="valider"><img src="../Include/valider.png" alt=""></button></td>
                        <td class="supprimer"><button type="submit" name="bouton" value="supprimer"><img src="../Include/refus.png" alt=""></button></td>
                    </tr>
                </form>
            <?php } ?>
        </table>
    <?php
    }
    ?>
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