<html>
    <body>
        <h2>owners</h2>

        @foreach($owners as $owner)
            Nome: {{ $owner->nome }}
            <a href="{{ route('owners.show', $owner->id)}}">dettagli</a>
            <a href="{{ route('owners.edit', $owner->id)}}">modifica</a>
        @endforeach
        <br>

        <form action="{{ route('owners.create') }}">
            <button>crea</button>
        </form>
    </body>
</html>
