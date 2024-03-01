<html>
    <body>
        <h1>Crea Nuovo Gioco</h1>

        <form action="{{ route('games.store') }}" method="POST">
            @csrf

            Nome:<input type="text" name="nome" required>
            Giocatore:</label>
            <select name="player" required>
                @foreach($players as $player)
                    <option value="{{ $player->id }}">{{ $player->nome }}</option>
                @endforeach
            </select>
            Prezzo:<input type="text" name="prezzo" required>
            <button type="submit">Crea Gioco</button>
        </form>
    </body>
</html>
