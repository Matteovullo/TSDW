<html>
    <body>
        <h2>owners</h2>

        <p>Nome: {{ $owner->nome }}</p>
        <p>Cognome: {{ $owner->cognome }}</p>
        <p>ID: {{ $owner->id }}</p>

        <form action="{{ route('owners.destroy', $owner->id) }}" method="post">
            @csrf
            @method('delete')
            <button>elimina</button>
        </form>
    </body>
</html>
