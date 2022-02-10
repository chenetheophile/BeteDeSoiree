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
    <link rel="shortcut icon" href="../Include/logo_appli.png" type="image/x-icon">
    <title>Bête De Soirée</title>
</head>

<body>
    <?php include("../Include/Entete.php");
    ?>
    <div class="boite">
        <img class="banniere" src="../Include/banniere.png" />
    </div>
    <div class="qui">
        <br>

        <h1>Bonjour et bienvenue sur notre page d'application Bête de Soirée !</h1>
        <h2>Qui somme nous?</h2>
        <blockquote>
            <h3>Eh bien nous sommes deux élèves qui avons crée ce projet.</h3>
        </blockquote>
        <h2>Quel est le but de l'appli ?</h2>
        <blockquote>
            <h3>L'idée de base était de créer une application pouvant nous aider lors de nos soirée entre copains. Nous avons alors décidé de plusieurs fonctionnalité comme la proposition de cocktail ou de recette. De la même manière nous avons chercher de quoi nous amuser a travers des jeux comme le loup garou!</h3>
        </blockquote>

        <br>
    </div>
</body>

</html>