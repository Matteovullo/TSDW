<html>

    <body>
        @foreach($players as $player)
            Nome: {{ $player->nome }}
            <a href="{{ route('players.show', $player->id) }}">mostra</a>
            <a href="{{ route('players.edit', $player->id) }}">modifica</a>
        @endforeach

        <a href="{{ route('players.create') }}">crea</a>

    </body>

</html>
