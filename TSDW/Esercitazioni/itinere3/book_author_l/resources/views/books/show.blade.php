<html>
    <body>
        <h1>Books</h1>

        <p>Nome: {{ $b->nome }}</p>
        <p>Prezzo: {{ $b->prezzo }}</p>
        <p>Volume: {{ $b->volume }}</p>
        <p>Autore: {{ $b->author }}</p>
        <p>Id: {{ $b->id }}</p>

        <form action="{{ route('books.destroy', $b->id)}}" method="post">
            @csrf
            @method('delete')
            <button>elimina</button>
        </form>

    </body>
</html>
