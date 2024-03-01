<html>
    <body>
        <h2>Vedi tutti</h2>
        <form action="/films" method="get">
            <input type="hidden" name="action" value="read">
            <button type="submit">visualizza</button>
        </form>

        <h2>Inserimento</h2>
        <form action="/films" method="post">
            Titolo: <input type="text" name="titolo" id="titolo">
            Anno: <input type="text" name="anno" id="anno">
            Paese: <input type="text" name="paese" id="paese">
            Regista: <input type="text" name="regista" id="regista">

            <input type="hidden" name="action" value="create">
            <button type="submit">inserisci</button>
        </form>

        <h2>Eliminazione</h2>
        <form action="/films" method="post">
            Titolo: <input type="text" name="titolo" id="titolo">

            <input type="hidden" name="action" value="delete">
            <button type="submit">elimina</button>
        </form>

    </body>
</html>
