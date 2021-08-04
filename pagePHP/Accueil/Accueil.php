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
            <th>Référence</th>
            <th>Nom</th>
            <th>Temps</th>
            <th>Description</th>
            <th>Ingredient</th>
            <th>Etape</th>
            <th>Lien</th>
        </tr>
        <?php
                include("../Include/Entete.php");
                include("../Include/get.php");
                //while ($result = $stmt->fetch(PDO::FETCH_ASSOC)) {
            ?>
        <tr>
            <td> <?php //print($result['Id']); ?></td>
            <td> <?php //print($result['Nom']); ?></td>
            <td> <?php //print($result['Temps']); ?></td>
            <td> <?php //print($result['Description']); ?></td>
            <td> <?php //print($result['Ingredient']); ?></td>
            <td> <?php //print($result['Etape']); ?></td>
            <td> <?php //print($result['Lien']); }?></td>
        </tr>
    </table>
</body>

</html>