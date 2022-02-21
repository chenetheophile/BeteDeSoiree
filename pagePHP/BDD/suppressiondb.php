<?php
    $db_hostname = 'localhost';
    if (strcmp($_SERVER['SERVER_NAME'], 'localhost')==0 or strcmp($_SERVER['SERVER_NAME'], '192.168.1.14') == 0) {
        $db_username = 'root';
        $db_password = 'root';
    } else {
        $db_username = 'theo';
        $db_password = '0908';
    }
    $db_dbname = 'bdes';
    $db_tablename = 'proposition';
    $db_conn_str = "mysql:host=" . $db_hostname . ";dbname=" . $db_dbname;
    $conn = new PDO($db_conn_str, $db_username, $db_password);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $id=$_POST["ID"];
    $stmt = $conn->prepare("SELECT `Nom`, `TempsPrepa`, `Description`, `Ingredient`, `Etape`, `Lien`, 
    `Princip`, `Alcool`, `Type`  FROM `proposition` WHERE `ID`=:id");
    $stmt->bindValue(':id',$id);
    $stmt->execute();
    $result = $stmt->fetch(PDO::FETCH_ASSOC);
    $nom=$result['Nom'];
    $Temps=$result['TempsPrepa'];
    $Desc=$result['Description'];
    $Ingr=$result['Ingredient'];
    $Etape=$result['Etape'];
    $Lien=$result['Lien'];
    $Princ=$result['Princip'];
    $Alcool=$result['Alcool'];
    $Type=$result['Type'];
    $stmt = $conn->prepare("DELETE FROM `proposition` WHERE `ID`=:id");
    $stmt->bindValue(':id',$id);
    $stmt->execute();
    print_r($_POST);
    if($_POST['bouton']=="valider"){
    ?>
    
    <form action="../BDD/put.php" method="POST" id="add">
        <input type="hidden" name="table" value="ajout"></input>
        <input type="hidden" name="nom" value="<?php echo $nom;?>"></input>
        <input type="hidden" name="temps" value="<?php echo $Temps;?>"></input>
        <input type="hidden" name="desc" value="<?php echo $Desc;?>"></input>
        <input type="hidden" name="ingr" value="<?php echo $Ingr;?>"></input>
        <input type="hidden" name="etape" value="<?php echo $Etape;?>"></input>
        <input type="hidden" name="lien" value="<?php echo $Lien;?>"></input>
        <input type="hidden" name="princ" value="<?php echo $Princ;?>"></input>
        <input type="hidden" name="alcool" value="<?php echo $Alcool;?>"></input>
        <input type="hidden" name="type" value="<?php echo $Type;?>"></input>
    </form>
    <script>print(document.getElementById('add').submit());</script>
    <?php }
    header("Location: ../Accueil/accueil.php")
?>
