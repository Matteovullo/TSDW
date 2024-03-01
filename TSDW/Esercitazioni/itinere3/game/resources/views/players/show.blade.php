<html>
    <body>
        <h1>Dettagli Giocatore</h1>

        <p><strong>ID:</strong> {{ $player->id }}</p>
        <p><strong>Nome:</strong> {{ $player->nome }}</p>

        <form action="{{ route('players.destroy', $player->id) }}" method="POST">
            @csrf
            @method('DELETE')
            <button type="submit">Elimina</button>
        </form>

        <a href="{{ route('players.index') }}"><button>Torna all'elenco</button></a>
    </body>
</html>
