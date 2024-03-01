<html>

    <body>
        <p>Nome: {{ $player->nome }}</p>
        <p>Eta: {{ $player->eta }}</p>
        <p>Id: {{ $player->id }}</p>

        <form action="{{ route('players.destroy', $player->id) }}" method="post">
            @csrf
            @method('delete')
            <button>elimina</button>
        </form>

    </body>

</html>
