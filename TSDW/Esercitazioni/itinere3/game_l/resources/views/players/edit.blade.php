<html>

    <body>

        <form action="{{ route('players.update', $player->id) }}" method="POST">
            @csrf
            @method('put')

            Nome:<input type="text" name="nome" required>
            Eta:<input type="text" name="eta" required>
            <button type="submit">modifica Giocatore</button>

        </form>

    </body>

</html>
