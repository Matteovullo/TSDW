<html>

    <body>
        <h1>Elenco squadre<h1>

        @foreach($players as $t)
            <p>
                Id: {{ $t->id }} - nome: {{ $t->nome }} - numero_di_maglia: {{ $t->n_maglia }} - teams: {{ $t-> team }}
                <a href="{{ route('players.show', $t->id) }}">Mostra</a>
                <a href="{{ route('players.edit', $t->id) }}">Modficia</a>
            </p>
        @endforeach

        <br><a href="{{ route('players.create') }}"><button> Nuovo players</button></a>

    </body>

</html>
