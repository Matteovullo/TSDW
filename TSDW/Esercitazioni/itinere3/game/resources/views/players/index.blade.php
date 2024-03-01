<html>
    <body>
        <h1>Elenco Giocatori</h1>

        @foreach ($players as $player)
            <p>
                ID: {{ $player->id }} - Nome: {{ $player->nome }}
                <a href="{{ route('players.show', $player->id) }}">Mostra</a>
                <a href="{{ route('players.edit', $player->id) }}">Modifica</a>
            </p>
        @endforeach

        <br><a href="{{ route('players.create') }}"><button> Nuovo Giocatore</button></a>
    </body>
</html>
