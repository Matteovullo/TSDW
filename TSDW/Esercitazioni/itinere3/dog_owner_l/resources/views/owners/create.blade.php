<html>
    <body>
        <h2>owners</h2>

        <form action="{{ route('owners.store') }}" method="post">
            @csrf
            <input name="nome" placeholder="nome" required>
            <input name="cognome" placeholder="cognome" required>
            <button>crea</button>
        </form>

    </body>
</html>
