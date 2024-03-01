<html>
    <head>
        <title>films</title>
    </head>
    <body>
        <h1>Film consigliato:</h1>
        <?php
                $conn=new mysqli("localhost", "root", "root", "myDB");

                if($conn->connect_error)
                    die("Connessione non riuscita".$conn->connect_error);

                $query="SELECT titolo, regista FROM flist ORDER BY RAND() LIMIT 1";
                $result=$conn->query($query);

                if($result->num_rows > 0){
                    while($rows=$result->fetch_assoc()){
                        echo "<p>Titolo: ".$rows["titolo"].", Regista: ".$rows["regista"]."</p>";
                    }
                }
        ?>

        <h1>Cerca un film:</h1>
        <form action="script.php" method="post">
            <label for="titolo">Titolo: </label>
            <input type="text" id="titolo" name="titolo">
            <br>

            <label for="regista">Regista: </label>
            <input type="text" id="regista" name="regista">
            <br>
            <button type="submit">cerca</button>
            <input type="hidden" name="action" value="read">
        </form>

        <h1>Lista dei desideri: </h1>
        <form action="bonus.php" method="get">
            <button type="submit">visualizza</button>
            <input type="hidden" name="action" value="read">
        </form>

        <h1>Svuota lista dei desideri: </h1>
        <form action="bonus.php" method="post">
            <button type="submit">svuota</button>
            <input type="hidden" name="action" value="delete">
        </form>
    </body>
</html>