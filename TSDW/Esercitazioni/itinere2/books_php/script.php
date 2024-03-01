<?php

$hostname="localhost";
$username_db="root";
$password_db="root";
$name_db="Library";

$conn=new mysqli($hostname, $username_db, $password_db, $name_db);

if($conn->connect_error)
    die("Connessione fallita".$conn->connect_error);

if($_SERVER["REQUEST_METHOD"] == "POST"){
    if($_POST["action"] == "create"){
        $isbn=$_POST["isbn"];
        $titolo=$_POST["titolo"];
        $autore=$_POST["autore"];
        $prezzo=$_POST["prezzo"];

        $query="INSERT INTO books (isbn, titolo, autore, prezzo) VALUES (?, ?, ?, ?)";
        $stmt=$conn->prepare($query);
        $stmt->bind_param("issi", $isbn, $titolo, $autore, $prezzo);
        $stmt->execute();

        if($stmt->affected_rows > 0)
            echo "Libro aggiunto correttamente";
        else 
            echo "Libro non aggiunto";

        $stmt->close();
    }

    if($_POST["action"] == "delete"){
        $isbn=$_POST["isbn"];
        $query="DELETE FROM books WHERE isbn=?";
        $stmt=$conn->prepare($query);
        $stmt->bind_param("i", $isbn);
        $stmt->execute();

        if($stmt->affected_rows>0)
            echo "Libro rimosso con successo";
        else 
            echo "Libro non rimosso";

        $stmt->close();
    }

    if ($_POST["action"] === "update") 
    {
        $titolo = $_POST["titolo"];
        $autore = $_POST["autore"];
        $prezzo = $_POST["prezzo"];

        $query = "UPDATE books SET autore = ?, prezzo = ? WHERE titolo = ?";
        $stmt = $conn->prepare($query);
        $stmt->bind_param("sis", $autore, $prezzo, $titolo);
        $stmt->execute();

        if ($stmt->affected_rows > 0) 
            echo "<p>Libro " . $titolo . " aggiornato</p>";
        else 
            echo "<p>Aggiornamento del libro " . $titolo . " non riuscito</p>";

        $stmt->close();
    }
}

if ($_SERVER["REQUEST_METHOD"] == "GET") 
{
    if ($_GET["action"] === "read") 
    {
        $query = "SELECT * FROM books";
        $result = $conn->query($query);
        if ($result->num_rows > 0) 
        {
            echo "<h2>Libri presenti:</h2>";
            while ($row = $result->fetch_assoc()) 
            {
                $titolo = $row["titolo"];
                echo "<p>ISBN <a href='redirect.php?titolo=$titolo'>" . $row["isbn"] . "</a>: " . $row["titolo"] . ", Autore: " . $row["autore"] . ", Prezzo: " . $row["prezzo"] . "</p>";
            }
        }
        else
            echo "<p>Nessun libro trovato nella biblioteca.</p>";
    }
}

$conn->close();
?>