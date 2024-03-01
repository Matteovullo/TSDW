<html>
    <body>
        <h1>Books</h1>

        <form action="{{ route('books.update', $b->id) }}" method="post">
            @csrf
            @method('put')
            <input name="nome" placeholder="nome" required>
            <input name="prezzo" placeholder="prezzo" required>
            <input name="volume" placeholder="volume" required>
            autore:
            <select name="author" required>
                @foreach($authors as $a)
                    <option value="{{ $a->id }}">{{ $a->nome }}</option>
                @endforeach
            </select>
            <button>crea</button>
        </form>

    </body>
</html>
