<html>
    <body>
        <h1>Books</h1>

        @foreach($books as $book)
        <form>
            <p>Name: {{$book->nome}}</p>
            <button formaction="{{route('books.show', $book->id)}}" method="GET">Show</button>
            <button formaction="{{route('books.edit', $book->id)}}" method="GET">Edit</button>
        </form>
        @endforeach

        <br>

        <form action="{{route('books.create')}}" method="GET">
            <button>Create new client</button>
        </form>

        <form method="post" action="{{ url('/books/deleteAll') }}">
            @csrf
            <button type="submit">Elimina tutti</button>
        </form>

        <form method="post" action="{{ url('/books/decrease') }}">
            @csrf
            <input type="hidden" name="percentage" value="20"> <!-- Modifica il valore della percentuale a tuo piacimento -->
            <button type="submit">Decrementa prezzo del 10%</button>
        </form>

    </body>
</html>
