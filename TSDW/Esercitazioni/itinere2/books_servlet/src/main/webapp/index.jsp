<html>
    <body>
        <h2>Books</h2>
        <h4>Visualizza la lista dei libri</h4>
        <a href="/servlet"><button>Vedi tutti</button></a>

        <h4>Inserimento</h4>
        <form action="/servlet" method="post">
            <label for="titolo">Titolo:</label>
            <input type="text" id="titolo" name="titolo" required><br>
            
            <label for="autore">Autore:</label>
            <input type="text" id="autore" name="autore" required><br>
            
            <label for="prezzo">Prezzo:</label>
            <input type="text" id="prezzo" name="prezzo" required><br>
            <br>
            <button type="submit">Registra</button>
            <input type="hidden" name="action" value="create">
        </form>
    </body>

</html>
