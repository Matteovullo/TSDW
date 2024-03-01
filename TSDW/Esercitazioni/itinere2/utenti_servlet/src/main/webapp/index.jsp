<html>
    <head>
        <title>Utenti</title>
    </head>
    <body>
        <h3>Visualizza tutto</h3>
        <form action="/servlet" method="get">
            <button type="submit">Visualizza tutto</button>
            <input type="hidden" name="action" value="read">
        </form>

        <h3>Inserimento nuovo utente</h3>
        <form action="/servlet" method="post">
            <label for="username">Name:</label>
            <input type="text" name="username" id="username">
            <br>

            <label for="password">Password:</label>
            <input type="text" name="password" id="password">
            <br>

            <button type="submit">inserisci</button>
            <input type="hidden" name="action" value="create">
        </form>

        <h3>Elinazione record</h3>
        <form action="/servlet" method="post">
            <label for="username">Name:</label>
            <input type="text" name="username" id="username">
            <br>

            <label for="password">Password:</label>
            <input type="text" name="password" id="password">
            <br>

            <button type="submit">elimina</button>
            <input type="hidden" name="action" value="delete">
        </form>

        <h3>Aggiornamento</h3>
        <form action="/servlet" method="post">
            <label for="username">Vecchio Name:</label>
            <input type="text" name="username" id="username">
            <br>

            <label for="password">Vecchia Password:</label>
            <input type="text" name="password" id="password">
            <br>

            <label for="n_username">Nuovo Name:</label>
            <input type="text" name="n_username" id="n_username">
            <br>

            <label for="n_password">Nuova Password:</label>
            <input type="text" name="n_password" id="n_password">
            <br>            

            <button type="submit">modifica</button>
            <input type="hidden" name="action" value="update">
        </form>
    </body>
</html>
