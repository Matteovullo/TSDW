<html>
    <body>
        <h1>Player creazione</h1>

        <form action="{{ route('players.store') }}" method="post">
            @csrf

            Nome: <input type="text" name="nome" required>
            Numero_di_maglia: <input type="text" name="n_maglia" required>
            Team: <select name="team" required>
                @foreach($teams as $team)
                    <option value="{{ $team->id }}">
                        {{ $team->nome }}
                    </option>
                @endforeach
            </select>
            <button type="submit">crea</button>
        </form>
    </body>
</html>
