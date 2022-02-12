<nav id="menu">
    <div class="element_menu">
        <ul>
            <li class="Option"><a href="../Accueil/accueil.php">Accueil</a></li>
            <?php if ($_SESSION["connecte"] == TRUE) { ?>
                <li><a href="../Login/deconnexion.php">Se deconnecter</a></li>
                <?php
                if ($_SESSION["statut"] == "ADMIN") {
                ?>
                    <li><a href="../BDD/ajoutdb.php">Ajouter dans la base</a></li>
                    <li><a href="../BDD/Proposition.php">Voir les propositions</a></li>
                <?php
                }
            } else { ?>
                <li class="Option"><a href="../Login/login.php">Connexion</a></li>
            <?php } ?>
            <li class="Option"><a href="../Jeux/jeux.php">Jeux disponible</a></li>
            <li class="Option"><a href="../Recette/recette.php">Recette</a></li>
            <li class="Option"><a href="../Cocktail/cocktail.php">Cocktail</a></li>
        </ul>
    </div>
</nav>