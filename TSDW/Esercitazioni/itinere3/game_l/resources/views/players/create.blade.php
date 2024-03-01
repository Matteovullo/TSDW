<html>

    <body>

        <form action="{{ route('players.store') }}" method="POST">
            @csrf

            Nome:<input type="text" name="nome" required>
            Eta:<input type="text" name="eta" required>
            <button type="submit">Crea Giocatore</button>

        </form>

    </body>

</html>
