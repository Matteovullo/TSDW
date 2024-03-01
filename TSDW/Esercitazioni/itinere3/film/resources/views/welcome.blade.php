<html>

    <body>
        <h1>Film</h1>
        <form action="/read" method="get">
            <button>Vedi tutti</button>
        </form>

        <form action="/insert" method="post">
            @csrf
            nome: <input type="text" id="nome" name="nome">
            cognomenome: <input type="text" id="cognome" name="cognome">
            data_nascita: <input type="date" id="data_nascita" name="data_nascita">
            <button>inserisci</button>
        </form>
    </body>

</html>