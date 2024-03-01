<?php

$conn=new mysqli("localhost", "root", "root", "myDB");

if($conn->connect_error)
    die("Connessione non riuscita".$conn->connect_error);

if($_SERVER["REQUEST_METHOD"] == "POST"){
    if(isset($_POST['conferma'])){
        $titolo=$_POST["titolo"];
        $regista=$_POST["regista"];
        $query="INSERT INTO wlist (titolo, regista) VALUES (?, ?)";
        $stmt=$conn->prepare($query);
        $stmt->bind_param("ss", $titolo, $regista);
        $stmt->execute();

        if($stmt->affected_rows > 0){
            echo "Film aggiunto alla wish list";
        }else{
            echo "Film non aggiunto alla wish list";
        }
    }elseif (isset($_POST['annulla'])) {
        header("Location: index.php");
        exit(); 
    }
}

?>