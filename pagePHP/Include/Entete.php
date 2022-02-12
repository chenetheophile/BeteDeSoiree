<?php session_start();
if ($_SESSION["connecte"] == FALSE) {
    session_unset();
};
?>
<header>
<img class="logosite" src="../Include/Image/logo_appli.png"/>
<h2 class="titre"> L'appli pour des soirées endiablées</h2>
</header>
<?php 
include("../Include/navigation.php");
?>