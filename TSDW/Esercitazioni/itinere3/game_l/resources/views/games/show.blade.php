<html>

    <body>
        <p>Nome: {{ $game->nome }}</p>
        <p>Prezzo: {{ $game->prezzo }}</p>
        <p>Id: {{ $game->id }}</p>
        <p>Player: {{ $game->player }}</p>

        <form action="{{ route('games.destroy', $game->id) }}" method="post">
            @csrf
            @method('delete')
            <button>elimina</button>
        </form>

    </body>

</html>
