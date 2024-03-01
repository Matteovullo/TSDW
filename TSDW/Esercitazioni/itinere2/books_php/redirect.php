<?php
$titolo = htmlspecialchars($_GET['titolo']);
?>

<html>
    <head>
      <title>Books</title>
    </head>
    <body>

        <h1>Form di aggiornamento</h1>
        <br>
    
        <strong><h3>Possibilit&agrave; di modificare autore e prezzo</h3></strong>
        <form action="script.php" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="titolo" value="<?php echo $titolo; ?>">
            <br>            
            <label for="autore">Autore:</label>
            <input type="text" id="autore" name="autore" required>
            <br>
            <label for="prezzo">Prezzo:</label>
            <input type="text" id="prezzo" name="prezzo" required>
            <br>    
            <br>
            <button type="submit">Aggiorna</button>
        </form> 

    </body>
</html>