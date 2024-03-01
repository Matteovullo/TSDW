<?php

$conn=new mysqli("localhost", "root", "root", "Cinema");

if($conn->connect_error)
    die("Conenssione non riuscite".$conn->connect_error);

function createFilm($conn, $titolo, $anno, $paese, $regista){
    $query="INSERT INTO films (titolo, anno, paese, regista) VALUE (?, ?, ?, ?)";
    $stmt=$conn->prepare($query);
    $stmt->bind_param("ssss", $titolo, $anno, $paese, $regista);
    $stmt->execute();

    if($stmt->affected_rows > 0)
        echo "Libro aggiunto correttamente";
    else
        echo "Libro non aggiunto";

    $stmt->close();
}
    
function readlFilms($conn){
    $query="SELECT * FROM films";
    $result=$conn->query($query);
    
    if($result->num_rows > 0){
        while($rows=$result->fetch_assoc()){
            echo "<p>Titolo: <a href='redirect.php?titolo=".$rows["titolo"]."'>".$rows["titolo"]."</a>".
            ", Anno: ".$rows["anno"].
            ", Paese: ".$rows["paese"].
            ", Regista: ".$rows["regista"]."</p>";
        }
    }else{
        echo "Nessun record presente";
    }
}

function updateFilms($conn, $titolo, $anno, $paese, $regista) {
    $query = "UPDATE films SET anno = ?, paese = ? , regista = ? WHERE titolo = ?";
    $stmt = $conn->prepare($query);
    $stmt->bind_param("ssss", $anno, $paese, $regista, $titolo);
    $stmt->execute();   

    if ($stmt->affected_rows > 0)
        echo "<p>Film aggiornato correttamente</p>";
    else   
        echo "<p>Errore nell'aggiornamento del film</p>";

    $stmt->close();
}

function deleteFilms($conn, $titolo){
    $query="DELETE FROM films WHERE titolo=?";
    $stmt=$conn->prepare($query);
    $stmt->bind_param("s", $titolo);
    $stmt->execute();

    if($stmt->affected_rows > 0)
        echo "Films eliminato correttamente";
    else 
        echo "Films non eliminato";
}

if($_SERVER["REQUEST_METHOD"] == "POST"){
    if($_POST["action"] == "create"){
        createFilm($conn, $_POST["titolo"], $_POST["anno"], $_POST["paese"], $_POST["regista"]);
    }
    if($_POST["action"] == "update"){
        updateFilms($conn, $_POST["titolo"], $_POST["anno"], $_POST["paese"], $_POST["regista"]);
    }
    if($_POST["action"] == "delete"){
        deleteFilms($conn, $_POST["titolo"]);
    }
    $conn->close();
}

if($_SERVER["REQUEST_METHOD"] == "GET"){
    if($_GET["action"] == "read"){
        readlFilms($conn);
    }
    $conn->close();
}


?>