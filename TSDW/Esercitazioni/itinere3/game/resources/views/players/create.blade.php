<html>
    <body>
        <h1>Crea Nuovo Giocatore</h1>

        <form action="{{ route('players.store') }}" method="post">
            @csrf

            Nome:<input type="text" name="nome" required>
            <button type="submit">Crea Giocatore</button>
        </form>
    </body>
</html>
