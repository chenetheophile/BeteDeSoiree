<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="login.css">
    <link rel="shortcut icon" href="../Include/logo_appli.png" type="image/x-icon">
    <title>Connexion</title>
</head>

<body>
    <?php
    include("../Include/Entete.php");
    if (isset($_GET['err'])) {
        $err = $_GET['err'];
        if ($err == 1)
            echo "<p style='color:red'>Utilisateur ou mot de passe incorrect</p>";
    }
    ?>
    <table class="tabConnexion">
        <form action="connexion.php" method="POST">
            <tr>
                <th><label for="labelID">Identifiant :</label></th>
                <th><input class='champ' type='text' name='login' required id='id' /></th>
            </tr>
            <tr>
                <th><label for="labelmdp">Mot de passe :</label></th>
                <th> <input class='champ' type='password' name='motdepasse' required id='idmdp' /></th>
            </tr>
            <tr>
                <th></th>
                <th  ><input class="bouton" id='idconnex' type='submit' value="S'identifier" name='connex' required  /></th>
            </tr>
        </form>
    </table>
</body>