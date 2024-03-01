<?php

$hostname="localhost";
$username_db="root";
$password_db="root";
$name_db="Film";

$conn=new mysqli($hostname, $username_db, $password_db, $name_db);

if($conn->connect_error)
    die("Connessione non riuscita".$conn->connect_error);

    $id_attore = 0;

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    if ($_POST["action"] == "create") {
            $nome = $_POST["nome"];
            $cognome = $_POST["cognome"];
            $data_nascita = $_POST["data"];
    
            $query_max_id = "SELECT MAX(id_attore) AS max_id FROM attori";
            $result_max_id = $conn->query($query_max_id);
    
            if ($result_max_id && $result_max_id->num_rows > 0) {
                $row = $result_max_id->fetch_assoc();
                $id_attore = $row['max_id'] + 1;
            } else {
                $id_attore = 1;
            }
    
            $query = "INSERT INTO attori (id_attore, nome, cognome, data_nascita) VALUES (?, ?, ?, ?)";
            $stmt = $conn->prepare($query);
            $stmt->bind_param("isss", $id_attore, $nome, $cognome, $data_nascita);
            $stmt->execute();
    
            if ($stmt->affected_rows > 0) {
                echo "Attore aggiunto correttamente";
            } else {
                echo "Attore non aggiunto correttamente";
            }
    
            $stmt->close();
        }
} 

if ($_SERVER["REQUEST_METHOD"] == "GET") {
    if (isset($_GET["action"]) && $_GET["action"] == "read") {
        $query = "SELECT * FROM attori";
        $result = $conn->query($query);

        if ($result && $result->num_rows > 0) {
            while ($row = $result->fetch_assoc()) {
                echo "<p>Nome: " . $row["nome"] . ", Cognome: " . $row["cognome"] . ", Data di nascita: " . $row["data_nascita"] . "</p>";
            }
        } else {
            echo "Nessun record trovato";
        }
    } else {
        echo "Azione di lettura non specificata correttamente";
    }
}

$conn->close();
?>