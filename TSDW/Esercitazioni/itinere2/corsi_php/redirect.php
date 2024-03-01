<?php
    $nome = $_GET["nome"];
?>

<html>
    <body>
        <h2>Pagina di aggiornamento</h2>

        <p>Aggiorna corso: </p>
        <form action="script.php" method="POST">
            <label for="descrizione">Descrizione:</label>
            <input type="text" id="descrizione" name="descrizione" required><br>
            <label for="crediti">Crediti:</label>
            <input type="text" id="crediti" name="crediti" required><br><br>
            <button>Aggiorna corso</button>
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="nome" value="<?php echo $nome?>">
        </form><br>

        <p>Rimuovi corso: </p>
        <form action="script.php" method="POST">
            <button>Rimuovi corso</button>
            <input type="hidden" name="action" value="delete">
            <input type="hidden" name="nome" value="<?php echo $nome?>">
        </form>

    </body>
</html>