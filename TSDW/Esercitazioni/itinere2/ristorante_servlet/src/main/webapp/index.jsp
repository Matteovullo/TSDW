<html>
    <body>
        <h2>Inserimento cameriere</h2>
        <form action="/servlet" method="post">
            <label for="nome">Nome: </label>
            <input type="text" name="nome" id="nome"><br>

            <label for="cognome">Cognome: </label>
            <input type="text" name="cognome" id="cognome"><br>

            <label for="username">Username: </label>
            <input type="text" name="username" id="username"><br>

            <label for="password">Password: </label>
            <input type="text" name="password" id="password"><br>
            
            <button type="submit">inserisci</button>
            <input type="hidden" value="create" name="action">
        </form>

        <h2>Vedi tavoli</h2>
        <form action="/servlet" method="get">
            <button type="submit">vedi tutti</button>
            <input type="hidden" value="read_t" name="action">
        </form>

        <h2>Vedi cameriere</h2>
        <form action="/servlet" method="get">
            <button type="submit">vedi tutti</button>
            <input type="hidden" value="read_c" name="action">
        </form>
    </body>
</html>