<html>
    <body>
    <form action="{{ route('games.update', $game->id) }}" method="post">
        @csrf
        @method('put')

        <label for="nome">Nome:</label>
        <input type="text" name="nome" value="{{ $game->nome }}" required>

        <label for="player">Giocatore:</label>
        <select name="player" required>
            @foreach($players as $player)
                <option value="{{ $player->id }}" {{ $player->id == $game->player ? 'selected' : '' }}>
                    {{ $player->nome }}
                </option>
            @endforeach
        </select>

        <label for="prezzo">Prezzo:</label>
        <input type="text" name="prezzo" value="{{ $game->prezzo }}" required>

        <button type="submit">Modifica Gioco</button>
    </form>
    </body>
</html>
