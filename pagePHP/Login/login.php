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
    <?php
    if (isset($_GET['err'])) {
        $err = $_GET['err'];
        if ($err == 1)
            echo "<p style='color:red'>Utilisateur ou mot de passe incorrect</p>";
    }
    ?>
    <form action="connexion.php" method="POST">
    <label for="labelID">Identifiant :</label>
    <input type='text' name='login' required id='id' />

    <label for="labelmdp">Mot de passe :</label>
    <input type='password' name='motdepasse' required id='idmdp' />

    <input type='submit' name='connex' required id='idconnex' />
    </form>
</body>