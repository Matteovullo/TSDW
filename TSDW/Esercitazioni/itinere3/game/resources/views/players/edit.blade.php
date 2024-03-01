<html>
    <body>
        <h1>Modifica Giocatore</h1>

        <form action="{{ route('players.update', $player->id) }}" method="post">
            @csrf
            @method('PUT')

            Nome:<input type="text" name="nome" value="{{ $player->nome }}">

            <button type="submit">Modifica Giocatore</button>
        </form>
    </body>
</html>
