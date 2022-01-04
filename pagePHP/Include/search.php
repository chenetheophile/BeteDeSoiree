<?php 
$recherche=$_POST['recherche'];
$token=$_POST['token'];
$longueur=$_POST['longueur'];
$url="https://api.spotify.com/v1/search?q=".$recherche."&type=track&market=FR&limit=".$longueur."&access_token=".$token;
$content=file_get_contents($url);
print_r($content);
?>