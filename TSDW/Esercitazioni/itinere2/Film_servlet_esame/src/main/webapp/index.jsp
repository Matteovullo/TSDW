<!--
    Implementare uno script in PHP che consenta di gestire un database di film. 
    Il database di film dovrebbe avere i seguenti campi:

    Titolo
    Anno
    Paese
    Regista

    Le funzioni richieste sono:

    - CreateFilm(): Permette di inserire un nuovo record nel database con le informazioni specificate.

    - ReadAllFilms(): Restituisce tutti i film presenti nel database.

    - UpdateFilm(): Aggiorna le informazioni di un film nel database in base al vecchio titolo.

    - DeleteFilm(): Elimina un film dal database in base al titolo.

-->

<html>
    <body>
        <h1>Films</h1>
        <strong><p>Benvenuto</p></strong>

        <!-- Create -->
        <form action="/servlet" method="post">
            <label for="titolo">Titolo:</label>
            <input type="text" id="titolo" name="titolo"><br>

            <label for="anno">Anno:</label>
            <input type="text" id="anno" name="anno"><br>

            <label for="paese">Paese:</label>
            <input type="text" id="paese" name="paese"><br>

            <label for="regista">Regista:</label>
            <input type="text" id="regista" name="regista"><br><br>

            <button type="submit">Inserisci</button>
            <input type="hidden" name="action" value="create">
        </form>

        <!-- Read -->
        <form action="/servlet" method="get">
            <button type="submit">Vedi tutti</button>
        </form>

    </body>
</html>