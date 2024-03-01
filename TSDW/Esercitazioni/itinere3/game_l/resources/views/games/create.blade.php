<html>

    <body>

        <form action="{{ route('games.store') }}" method="POST">
            @csrf

            Nome:<input type="text" name="nome" required>
            Prezzo:<input type="text" name="prezzo" required>
            Giocatore:
            <select name="player" required>
                @foreach($players as $player)
                    <option value="{{ $player->id }}">{{ $player->nome }}</option>
                @endforeach
            </select>
            <button type="submit">Crea</button>

        </form>

    </body>

</html>
