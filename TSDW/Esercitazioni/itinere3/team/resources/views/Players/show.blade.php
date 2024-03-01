<html>

    <body>
        <h1>Elenco squadre<h1>

            <p>
                Id: {{ $player->id }} - nome: {{ $player->nome }} - numero_di_maglia: {{ $player->n_maglia }} - teams: {{ $player->teams }}
            </p>

            <form action="{{ route('players.destroy', $player->id) }}" method="post" style="display:inline;">
                @csrf
                @method('delete')
                <button type="submit">Elimina</button>
            </form><br>

    </body>

</html>
