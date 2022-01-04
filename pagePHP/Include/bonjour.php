<?php
if (isset($_POST['i'])) {
    $i = $_POST['i'];
} else {
    $i = 200;
}

while ($i < 499) {
?>
    <form id="lol" action="../Include/put.php" method="POST">
        <input type="hidden" name="nom" value="<?php print($i); ?>">
        <input type="hidden" name="temps" value="<?php print($i); ?>">
        <input type="hidden" name="desc" value="<?php print($i); ?>">
        <input type="hidden" name="ingr" value="<?php print($i); ?>">
        <input type="hidden" name="etape" value="<?php print($i); ?>">
        <input type="hidden" name="lien" value="<?php print($i); ?>">
        <input type="hidden" name="princ" value="<?php print($i); ?>">
        <input type="hidden" name="type" value="Recette">
        <input type="hidden" name="table" value="lol">
        <input type="hidden" name="alcool" value="0">
        <input type="hidden" name="i" value="<?php print($i); ?>">


    </form>
    <script>
        document.getElementById("lol").submit();
    </script>
<?php

}


?>