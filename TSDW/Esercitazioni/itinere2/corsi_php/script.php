<?php

$conn=new mysqli("localhost", "root", "root", "uni");

if($conn->connect_error)
    die("Conessione non riuscita ".$conn->connect_error);

if($_SERVER["REQUEST_METHOD"]=="GET"){
    if($_GET["action"]=="read"){
        $query="SELECT * FROM courses";
        $result=$conn->query($query);

        if($result->num_rows > 0){
            while($rows=$result->fetch_assoc()){
                echo "<p>Codice: <a href='redirect.php?nome=".$rows["nome_corso"]."'>".$rows["codice_corso"]."</a>".
                ", Nome:".$rows["nome_corso"].
                    ", descrizione: ".$rows["descrizione"].", crediti: ".$rows["crediti"]."</p>";
            }
        }
    }
}

if($_SERVER["REQUEST_METHOD"]=="POST"){
    if($_POST["action"]=="create"){
        $query="INSERT INTO courses (nome_corso, descrizione, crediti) VALUES (?, ?, ?)";
        $stmt=$conn->prepare($query);
        $stmt->bind_param("sss", $_POST["nome_corso"], $_POST["descrizione"], $_POST["crediti"]);
        $stmt->execute();

        if($stmt->affected_rows > 0){
            echo "Corso inserito corretamente";
        }
    }

    if($_POST["action"]=="delete"){
        $query="DELETE FROM courses WHERE nome_corso=?";
        $stmt=$conn->prepare($query);
        $stmt->bind_param("s", $_POST["nome"]);
        $stmt->execute();

        if($stmt->affected_rows > 0){
            echo "Corso eliminato corretamente";
        }
    }

    if($_POST["action"]=="update"){
        $query = "UPDATE courses SET descrizione=?, crediti=? WHERE nome_corso=?";
        $stmt=$conn->prepare($query);
        $stmt->bind_param("sss", $_POST["descrizione"], $_POST["crediti"], $_POST["nome"]);
        $stmt->execute();

        if($stmt->affected_rows > 0){
            echo "Corso aggirnato corretamente";
        }
    }
}

?>