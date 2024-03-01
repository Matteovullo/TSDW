<?php

$conn=new mysqli("localhost", "root", "root", "Database");

if($conn->connect_error){
    die("Connessione al database non riuscita".$conn->connect_error);
}

if($_SERVER["REQUEST_METHOD"] == "GET"){
    if($_GET["action"]=="read"){
        $query="SELECT * FROM utenti";
        $result=$conn->query($query);

        if($result->num_rows > 0){
            while($rows=$result->fetch_assoc()){
                echo "<p>Username: ".$rows["username"].", password:".$rows["password"]."</p>";
            }
        }
    }
}

if($_SERVER["REQUEST_METHOD"] == "POST"){
    if($_POST["action"]=="create"){
        $username=$_POST["username"];
        $password=$_POST["password"];
        $query="INSERT INTO utenti (username, password) VALUES (?, ?)";
        $stmt=$conn->prepare($query);
        $stmt->bind_param("ss", $username, $password);
        $stmt->execute();

        if($stmt->affected_rows > 0){
            echo "Utente aggiunto correttamente";
        }else{
            echo "Errore! Utente non aggiunto";
        }
    }

    if($_POST["action"]=="delete"){
        $username=$_POST["username"];
        $password=$_POST["password"];
        $query="DELETE FROM utenti WHERE username=? AND password=?";
        $stmt=$conn->prepare($query);
        $stmt->bind_param("ss", $username, $password);
        $stmt->execute();

        if($stmt->affected_rows > 0){
            echo "Utente eliminato correttamente";
        }else{
            echo "Errore! Utente non eliminato";
        }
    }

    if($_POST["action"]=="update"){
        $username=$_POST["username"];
        $password=$_POST["password"];
        $n_username=$_POST["n_username"];
        $n_password=$_POST["n_password"];

        $query="UPDATE utenti SET username=?, password=? WHERE username=? AND password=?";
        $stmt=$conn->prepare($query);
        $stmt->bind_param("ssss", $n_username, $n_password, $username, $password);
        $stmt->execute();

        if($stmt->affected_rows > 0){
            echo "Utente modificato correttamente";
        }else{
            echo "Errore! Utente non modificato";
        }
    }
}


?>