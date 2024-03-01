<html>

    <body>
        <h1>Elenco squadre<h1>

        @foreach($teams as $t)
            <p>
                Id: {{ $t->id }} - nome: {{ $t->nome }} - stadio: {{ $t->stadio }} - anno_fondazione: {{ $t-> anno_fondazione }}
                <a href="{{ route('teams.show', $t->id) }}">Mostra</a>
                <a href="{{ route('teams.edit', $t->id) }}">Modficia</a>
            </p>
        @endforeach

        <br><a href="{{ route('teams.create') }}"><button> Nuovo Teams</button></a>

    </body>

</html>
