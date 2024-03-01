<html>
    <body>
        <h2>Leggi tutti i corsi</h2>
        <form action="/servlet" method="get">
            <button type="submit">visualzza</button>
            <input type="hidden" value="read" name="action">
        </form>

        <h2>Inserimento corso</h2>
        <form action="/servlet" method="post">
            <label for="nome_corso">Nome corso:</label>
            <input type="text" id="nome_corso" name="nome_corso" required>
            <br>

            <label for="descrizione">Descrizone:</label>
            <input type="text" id="descrizione" name="descrizione" required>
            <br>

            <label for="crediti">Crediti:</label>
            <input type="text" id="crediti" name="crediti" required>
            <br>

            <button type="submit">inserisci</button>
            <input type="hidden" value="create" name="action">
        </form>
    </body>
</html>
