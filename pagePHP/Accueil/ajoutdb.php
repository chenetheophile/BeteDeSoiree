<?php session_start();
if ($_SESSION["connecte"] == FALSE) {
    session_unset();
};
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter dans la BDD</title>
    <link rel="shortcut icon" href="../Include/logo_appli.png" type="image/x-icon">
    <link rel="stylesheet" href="ajoutdb.css">
</head>
<body>
<?php include("../Include/Entete.php"); ?>
<table class="tableau">
            <tr>
                <th>Type</th>
                <th>Nom</th>
                <th>Temps</th>
                <th>Description</th>
                <th>Ingredient</th>
                <th>Principal</th>
                <th>Etape</th>
                <th>Lien</th>
            </tr>
                <form action="../Include/put.php" method="POST">
                    <tr>
                        <td class="case"> <input type="text" name="type"></td>
                        <td class="case"> <input type="text" name="nom"></td>
                        <td class="case"> <input type="text" name="temps"></td>
                        <td class="case"> <input type="text" name="desc"></td>
                        <td class="case"> <input type="text" name="ingr"></td>
                        <td class="case"> <input type="text" name="princ"></td>
                        <td class="case"> <input type="text" name="etape"></td>
                        <td class="case"> <input class="txtlien" type="text" name="lien"></input></td>
                        <input type="hidden" name="table" value="ajoutdb">
                        <input type="hidden" name="ajoutdb" value="true">
                        <td><input type="submit" name="Envoyer"></td>
                    </tr>
                    
                </form>
        </table>
</body>
</html>