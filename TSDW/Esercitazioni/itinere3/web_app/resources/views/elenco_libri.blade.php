<!-- resources/views/elenco_libri.blade.php -->
<html>
<head>
    <title>Elenchi di Libri</title>
</head>
<body>
    <h1>Elenchi di Libri</h1>
    <ul>
        @foreach($books as $book)
            <li>{{ $book->isbn }} {{ $book->titolo }} - {{ $book->autore }} - {{ $book->prezzo }}</li>
            <!-- Aggiungi altri campi del libro che desideri visualizzare -->
        @endforeach
    </ul>
</body>
</html>
