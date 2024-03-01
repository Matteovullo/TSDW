<html>

    <body>

        <form action="{{ route('games.update', $game->id) }}" method="POST">
            @csrf
            @method('put')

            Nome:<input type="text" name="nome" required>
            Prezzo:<input type="text" name="prezzo" required>
            Giocatore:
            <select name="player" required>
                @foreach($players as $player)
                    <option value="{{ $player->id }}">{{ $player->nome }}</option>
                @endforeach
            </select>
            <button type="submit">modifica</button>

        </form>

    </body>

</html>
