<?php

$conn=new mysqli("localhost", "root", "root", "myDB");

if($conn->connect_error)
    die("Connessione non riuscita".$conn->connect_error);

if($_SERVER["REQUEST_METHOD"] == "POST"){
    if($_POST["action"] == "read"){
        $titolo=$_POST["titolo"];
        $regista=$_POST["regista"];
        $query="SELECT * FROM flist WHERE titolo=? AND regista=?";
        $stmt=$conn->prepare($query);
        $stmt->bind_param("ss", $titolo, $regista);
        $stmt->execute();
        $result=$stmt->get_result();

        if($result->num_rows > 0){
            echo "<h1>film richiesto/i:</h1>";
            while($rows=$result->fetch_assoc()){
                echo "<p>Titolo: ".$rows["titolo"].", Regista: ".$rows["regista"]."<br>";
                echo "<button><a href='index.html'>Torna indietro</a></button>";
            }
        }else{
            echo "<form method='post' action='aggiungi_wishlist.php'>";
            echo "<input type='hidden' name='titolo' value='$titolo'>";
            echo "<input type='hidden' name='regista' value='$regista'>";
            echo "<input type='submit' name='conferma' value='Si'>";
            echo "<input type='submit' name='annulla' value='No'>";
            echo "</form>";
        }
    }
}

?>