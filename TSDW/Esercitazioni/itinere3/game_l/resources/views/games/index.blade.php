<html>

    <body>
        @foreach($games as $game)
            Nome: {{ $game->nome }}
            <a href="{{ route('games.show', $game->id) }}">mostra</a>
            <a href="{{ route('games.edit', $game->id) }}">modifica</a>
        @endforeach

        <a href="{{ route('games.create') }}">crea</a>

    </body>

</html>
