<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter dans la BDD</title>
    <link rel="shortcut icon" href="../Include/Image/logo_appli.png" type="image/x-icon">
    <link rel="stylesheet" href="ajoutdb.css">
</head>

<body>
    <?php include("../Include/Entete.php"); ?>
    <table class="tableau">
        <tr>
            <th>
                <h1>Type</h1>
            </th>
            <th>
                <h1>Nom</h1>
            </th>
            <th>
                <h1>Temps</h1>
            </th>
            <th>
                <h1>Description</h1>
            </th>
            <th>
                <h1>Ingredient</h1>
            </th>
            <th>
                <h1>Principal</h1>
            </th>
            <th>
                <h1>Etape</h1>
            </th>
            <th>
                <h1>Lien</h1>
            </th>
            <th>
                <h1></h1>
            </th>
        </tr>
        <form action="../Include/put.php" method="POST">
            <tr>
                <td class="case">
                    <p><input type="radio" id='radioRecette' value='Recette' name="type">
                        <label for="radioRecette">Recette</label>
                    </p>
                    <p><input type="radio" value='Cocktail' name="type" id="radioCocktail">
                        <label for="radioCocktail">Cocktail</label>
                    </p>

                </td>
                <td class="case"> <input type="text" name="nom"></td>
                <td class="case"> <input type="text" name="temps"></td>
                <td class="case"> <input type="text" name="desc"></td>
                <td class="case"> <input type="text" name="ingr"></td>
                <td class="case"> <input type="text" name="princ"></td>
                <td class="case"> <input type="text" name="etape"></td>
                <td class="case"> <input class="txtlien" type="text" name="lien"></input></td>
                <input type="hidden" name="table" value="ajoutdb">
                <input type="hidden" name="ajoutdb" value="true">
                <td><input class='bouton' type="submit" name="Envoyer"></td>
            </tr>

        </form>
    </table>
</body>

</html>