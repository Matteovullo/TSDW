<html>

    <body>
        <h2>Benvenuto</h2>

        @foreach($books as $book)
            <p>Nome: {{ $book->nome }}</p>
            <a href="{{ route('books.show', $book->id) }}"><button>dettagli</button></a>
            <a href="{{ route('books.edit', $book->id) }}"><button>modifica</button></a>
        @endforeach

        <a href="{{ route('books.create') }}"><button>crea</button></a>
    </body>

</html>
