<html>
    <body>
        <h1>Elenco Giochi</h1>

        @foreach ($games as $game)
            <p>
                ID: {{ $game->id }} - Nome: {{ $game->nome }} - Player-ID: {{ $game->player }} - Prezzo: {{ $game->prezzo }}
                <a href="{{ route('games.show', $game->id) }}">Mostra</a>
                <a href="{{ route('games.edit', $game->id) }}">Modifica</a>
            </p>
        @endforeach

        <a href="{{ route('games.create') }}">Crea Nuovo Gioco</a>
    </body>
</html>
