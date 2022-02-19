<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="login.css">
    <link rel="shortcut icon" href="../Include/Image/logo_appli.png" type="image/x-icon">
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
                <td>
                    <h1>Connexion</h1>
                </td>
            </tr>
            <tr>
                <th class='form-input-material'><input type='text' class="form-control-material" placeholder="Identifiant" name='login' required id='id' /></th>
            </tr>
            <tr>
                <th class='form-input-material'> <input type='password' class="form-control-material" placeholder="Mot de passe" name='motdepasse' required id='idmdp' /></th>
            </tr>
            <tr>
                <th>
                    <div class="container container-one">
                        <button>S'identifier <div class="fill-one"></div></button>
                    </div>
                </th>
            </tr>
        </form>
    </table>

</body>