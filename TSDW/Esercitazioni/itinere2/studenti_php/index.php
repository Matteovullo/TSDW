<html>
    <body>
    <h2>Lista studenti<h2>
    
    <?php
        $conn=new mysqli("localhost", "root", "root", "uni");

        if($conn->connect_error)
            die("Connessione non riuscita".$conn->connect_error);

        $query="SELECT * FROM students";
        $result = $conn->query($query);

        if($result->num_rows > 0){
            while($rows=$result->fetch_assoc()){
                echo "<p>Matricola: ".$rows["matricola"].", nome: ".$rows["nome"].
                ", cognome: ".$rows["cognome"].", corso_di_laurea: <a href='script.php?matricola=".
                $rows["matricola"]."&corso_di_laurea=".$rows["corso_di_laurea"]."'>".$rows["corso_di_laurea"]."</a></p>";
            }
        }


    ?>

     <h2>Inseriemento</h2>
        <form action="script.php" method="post">
            <label for="matricola">Matricola: </label>
            <input id="matricola" name="matricola" type="text">
            <br>

            <label for="nome">Nome: </label>
            <input id="nome" name="nome" type="text">
            <br>

            <label for="cognome">Cognome: </label>
            <input id="cognome" name="cognome" type="text">
            <br>

            <label for="corso_di_laurea">Corso di laurea: </label>
            <input id="corso_di_laurea" name="corso_di_laurea" type="text">
            <br>

            <button type="submit">inserisci</button>
            <input type="hidden" value="create" name="action">
        </form>
    </body>
</html>
