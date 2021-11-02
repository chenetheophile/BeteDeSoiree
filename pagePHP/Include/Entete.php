<header>
<img class="logosite" src="../Include/logo_appli.png"/>
</header>
<nav id="menu">        
    <div class="element_menu">
        <ul>
            <li class="Option"><a href="../Accueil/accueil.php">Accueil</a></li>
            <?php if($_SESSION["connecte"]==TRUE){?>
            <li><a href="../Login/deconnexion.php">se deconnecter</a></li>
            <?php }else{?>
            <li class="Option"><a href="../Login/login.php">connexion</a></li>
            <?php }?>
        </ul>
    </div>    
</nav>