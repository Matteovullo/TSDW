<html>
    <body>
        <h1>Authors</h1>

        <p>Nome: {{ $a->nome }}</p>
        <p>Cognome: {{ $a->cognome }}</p>
        <p>Eta: {{ $a->eta }}</p>
        <p>Id: {{ $a->id }}</p>

        <form action="{{ route('authors.destroy', $a->id)}}" method="post">
            @csrf
            @method('delete')
            <button>elimina</button>
        </form>

    </body>
</html>
