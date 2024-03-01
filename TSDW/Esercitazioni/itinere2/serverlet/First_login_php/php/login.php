<?php
    if ($_SERVER["REQUEST_METHOD"] == "POST")
    {
        session_start();

        $email = $_POST["email"];
        $password = $_POST["password"];

        $hostname = "localhost";
        $username_db = "root";
        $password_db = "root";
        $name_db = "Database";

        $conn = new mysqli($hostname, $username_db, $password_db, $name_db);
        if ($conn->connect_error)
            die("Connessione fallita: " . $conn->connect_error);
        
        $query = "SELECT *
                  FROM utenti 
                  WHERE email = '$email' AND password = '$password'";

        $outcome = $conn->query($query);
        
        if ($outcome->num_rows > 0)
        {
            $row = $outcome->fetch_assoc();
            $_SESSION["username"] = $row["username"];
            header("Location: welcome.php");
            exit();
        }
        else 
            echo "<i><p>Login non riuscito. Verifica le tue credenziali</p></i>";
        
        $conn->close();
    }
?>
