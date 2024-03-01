<!DOCTYPE html>
<html>
<head>
    <title>Benvenuto</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>
    <h1>Benvenuto!</h1>
    <?php
        session_start();
        
        if (!isset($_SESSION["id"])) // controlla se mail è null
        {
            header("Location: ./index.html");
            exit();
        }    
        echo "<p>Benvenuto " . $_SESSION["id"] . "!</p>";
    ?>
    <a href="./index.html">Logout</a>
</body>
</html>
