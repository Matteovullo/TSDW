<html>

    <body>
        <h1>Elenco squadre</h1>

            <p>
                Id: {{ $team->id }} - nome: {{ $team->nome }} - stadio: {{ $team->stadio }} - anno_di_fondazione: {{ $team-> anno_fondazione }}
            </p>

            <form action="{{ route('teams.destroy', $team->id) }}" method="post">
                @csrf
                @method('delete')

                <button type="submit">elimina</button>
            </form>

    </body>

</html>
