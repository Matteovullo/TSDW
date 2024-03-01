<html>

    <body>
        <h2>Benvenuto</h2>

        <p>Nome: {{ $book->nome }}</p>
        <p>Volume: {{ $book->volume }}</p>
        <p>Studente: {{ $book->student }}</p>

        <form action="{{ route('books.destroy', $book->id) }}" method="post">
            @csrf
            @method('delete')

            <button>elimina</button>
        </form>
    </body>

</html>
