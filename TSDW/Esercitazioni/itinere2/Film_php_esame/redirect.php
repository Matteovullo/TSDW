<?php
    $titolo = $_GET["titolo"];
?>

<html>
    <body>
        <h1>Films</h1>
        <strong><p>Modifica il film <?php echo $titolo;?></p></strong>

        <!-- Update -->
        <form action="script.php" method="post">
            <label for="anno">Anno:</label>
            <input type="text" id="anno" name="anno"><br>

            <label for="paese">Paese:</label>
            <input type="text" id="paese" name="paese"><br>

            <label for="regista">Regista:</label>
            <input type="text" id="regista" name="regista"><br><br>

            <button type="submit">Aggiorna</button>
            <input type="hidden" name="titolo" value="<?php echo $titolo;?>">
            <input type="hidden" name="action" value="update">
        </form><br>

        <strong><p>Rimuovi il film <?php echo $titolo;?></p></strong>

        <!-- Delete -->
        <form action="script.php" method="post">
            <button type="submit">Rimuovi</button>
            <input type="hidden" name="titolo" value="<?php echo $titolo;?>">
            <input type="hidden" name="action" value="delete">
        </form>

    </body>
</html>