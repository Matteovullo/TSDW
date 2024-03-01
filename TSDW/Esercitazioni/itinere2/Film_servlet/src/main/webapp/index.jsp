<html>
    <head>
        <title>Forum</title>
    </head>
    <body>
        <h2>Films</h2>
        <h4>Visualizza la lista degli attori</h4>
        <a href="/servlet"><button>Vedi tutti</button></a>

        <h4>Inserimento</h4>
        <form action="/servlet" method="post">
            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome" required><br>
            
            <label for="cognome">Cognome:</label>
            <input type="text" id="cognome" name="cognome" required><br>
            
            <label for="data_nascita">Data:</label>
            <input type="date" id="data_nascita" name="data_nascita" required><br>
            <br>
            <button type="submit">Registra</button>
            <input type="hidden" name="action" value="create">
        </form>    
    </body>
</html>