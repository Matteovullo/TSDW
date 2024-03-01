<html>
    <body>
        <h1>Authors</h1>

        <form action="{{ route('authors.update', $a->id)}}" method="post">
            @csrf
            @method('put')
            <input name="nome" placeholder="nome" required>
            <input name="cognome" placeholder="cognome" required>
            <input name="eta" placeholder="eta" required>
            <button>crea</button>
        </form>

    </body>
</html>
