<?php
    if ($_SERVER["REQUEST_METHOD"] == "POST")
    {
        session_start();

        $username = $_POST["username"];
        $password = $_POST["password"];

        $hostname = "localhost";
        $username_db = "root";
        $password_db = "root";
        $name_db = "my_database";

        $conn = new mysqli($hostname, $username_db, $password_db, $name_db);
        if ($conn->connect_error)
            die("Connessione fallita: " . $conn->connect_error);
        
        $query = "SELECT *
                  FROM users 
                  WHERE username = '$username' AND password = '$password'";

        $outcome = $conn->query($query);
        
        if ($outcome->num_rows > 0)
        {
            $row = $outcome->fetch_assoc();
            $_SESSION["id"] = $row["id"];
            header("Location: welcome.php");
            exit();
        }
        else 
            echo "<i><p>Login non riuscito. Verifica le tue credenziali</p></i>";
        
        $conn->close();
    }
?>
