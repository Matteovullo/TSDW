<?php

$conn=new mysqli("localhost", "root", "root", "myDB");

if($conn->connect_error)
    die("Connessione non riuscita".$conn->connect_error);

if($_SERVER["REQUEST_METHOD"] == "GET"){
    if($_GET["action"] == "read"){
        $query="SELECT * FROM wlist";
        $result=$conn->query($query);

        if($result->num_rows > 0){
            echo "<h1>film richiesto/i:</h1>";
            while($rows=$result->fetch_assoc()){
                echo "<p>Titolo: ".$rows["titolo"].", Regista: ".$rows["regista"]."<br>";
            }
        }else{
            echo "Nessun film nella lista dei desideri"."<br>";
        }

        echo "<button><a href='index.php'>Torna indietro</a></button>";
    }
}

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    if ($_POST["action"] == "delete") {
        $query = "DELETE FROM wlist";
        $result = $conn->query($query);

        if ($result) {
            if ($conn->affected_rows > 0) {
                echo "Wish list svuotata"."<br>";
            } else {
                echo "Nessun film nella lista dei desideri"."<br>";
            }
        } else {
            echo "Errore durante l'eliminazione: " . $conn->error."<br>";
        }


        echo "<button><a href='index.php'>Torna indietro</a></button>";
    }
}


?>