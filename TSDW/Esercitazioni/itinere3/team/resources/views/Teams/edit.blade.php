<html>
    <body>
    <form action="{{ route('teams.update', $team->id) }}" method="post">
        @csrf
        @method('put')

        <label for="nome">Nome:</label>
        <input type="text" name="nome" value="{{ $team->nome }}" required>

        <label for="stadio">Stadio:</label>
        <input type="text" name="stadio" value="{{ $team->stadio }}" required>

        <label for="anno_fondazione">anno:</label>
        <input type="text" name="anno_fondazione" value="{{ $team->anno_fondazione }}" required>

        <button type="submit">Modifica Team</button>
    </form>
    </body>
</html>
