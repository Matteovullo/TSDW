<?php

$conn=new mysqli("localhost", "root", "root", "uni");

if($conn->connect_error)
    die("Connessione non riuscita".$conn->connect_error);

if($_SERVER["REQUEST_METHOD"]=="POST"){
    if($_POST["action"] == "create"){
        $matricola=$_POST["matricola"];
        $nome=$_POST["nome"];
        $cognome=$_POST["cognome"];
        $corso_di_laurea=$_POST["corso_di_laurea"];

        $query="INSERT INTO students (matricola, nome, cognome, corso_di_laurea) VALUES (?, ?, ?, ?)";
        $stmt = $conn->prepare($query);
        $stmt->bind_param("ssss", $matricola, $nome, $cognome, $corso_di_laurea);
        $stmt->execute();

        print("Studente aggiunto correttamente"."<br>");
    }

    if($_POST["action"] == "delete"){
        $matricola=$_POST["matricola"];

        $query="DELETE FROM students WHERE matricola=?";
        $stmt = $conn->prepare($query);
        $stmt->bind_param("s", $matricola);
        $stmt->execute();

        print("Studente eliminato correttamente"."<br>");
    }

    if($_POST["action"] == "update"){
        $matricola=$_POST["matricola"];
        $corso_di_laurea=$_POST["corso"];

        $query="UPDATE students SET corso_di_laurea=? WHERE matricola=?";
        $stmt = $conn->prepare($query);
        $stmt->bind_param("ss", $corso_di_laurea, $matricola);
        $stmt->execute();

        print("Studente aggiornato correttamente"."<br>");
    }

    echo "<a href='index.php'><button>torna indietro</button></a>";
}

if($_SERVER["REQUEST_METHOD"]=="GET"){
    $matricola=$_GET["matricola"];
    $corso_di_laurea=$_GET["corso_di_laurea"];
    $query="SELECT * FROM courses WHERE codice_corso=?";
    $stmt = $conn->prepare($query);
    $stmt->bind_param("s", $corso_di_laurea);
    $stmt->execute();
    $result = $stmt->get_result();

    if($result->num_rows > 0){
        while($rows=$result->fetch_assoc()){
            echo "<p>Codice corso: ".$rows["codice_corso"].", nome corso: ".$rows["nome_corso"].
            ", descrizione: ".$rows["descrizione"].", crediti: ".$rows["crediti"]."</p>";
        }
    }

    print("<br><h3>Cambia corso di laurea</h3>".
                    "<form action='script.php' method='POST'>".
                        "<label for='corso'>Corso di laurea: </label>".
                        "<input type='text' id='corso' name='corso'><br><br>".
                        "<button type='submit'>Aggiorna corso</button>".
                        "<input type='hidden' name='action' value='update'>".
                        "<input type='hidden' name='matricola' value='".$matricola."'>".
                   "</form><br>");
    
    print("<br><h3>Rimuovi studente</h3>".
        "<form action='script.php' method='POST'>".
            "<button type='submit'>Rimuovi studente</button>".
            "<input type='hidden' name='action' value='delete'>".
            "<input type='hidden' name='matricola' value='".$matricola."'>".
    "</form><br>");
}

?>

