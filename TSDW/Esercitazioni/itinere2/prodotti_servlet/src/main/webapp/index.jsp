<html>
    <body>
        <h2>Inserimento</h2>
        <form action="/servlet" method="post">
            <label for="nome">Nome prodotto: </label>
            <input type="text" id="nome" name="nome" required><br>

            <label for="giacenza">Giacenza: </label>
            <input type="text" id="giacenza" name="giacenza" required><br>

            <label for="prezzo">Prezzo: </label>
            <input type="text" id="prezzo" name="prezzo" required><br>

            <button type="submit">inserisci</button>

            <input type="hidden" value="create" name="action">
        </form>

        <h2>Visualizza tutti i prodotti</h2>
        <form action="/servlet" method="get">
            <input type="hidden" value="read" name="action">
            <button type="submit">visualizza</button>
        </form>
    </body>
</html>
