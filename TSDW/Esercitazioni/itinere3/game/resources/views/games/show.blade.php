<html>
    <body>
        <h1>Dettagli Gioco</h1>

        <p>ID: {{ $game->id }}</p>
        <p>Nome: {{ $game->nome }}</p>
        <p>Giocatore: {{ $game->player }}</p>
        <p>Prezzo: {{ $game->prezzo }}</p>

        <form action="{{ route('games.destroy', $game->id) }}" method="post" style="display:inline;">
            @csrf
            @method('delete')
            <button type="submit">Elimina</button>
        </form><br>

        <br><a href="{{ route('games.index') }}"><button>Torna all'elenco</button></a>
    </body>
</html>
