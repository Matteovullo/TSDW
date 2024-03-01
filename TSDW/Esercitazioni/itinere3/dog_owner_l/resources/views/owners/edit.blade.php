<html>
    <body>
        <h2>owners</h2>

        <form action="{{ route('owners.update', $owner->id) }}" method="post">
            @csrf
            @method('put')
            <input name="nome" placeholder="nome" required>
            <input name="cognome" placeholder="cognome" required>
            <button>modifica</button>
        </form>

    </body>
</html>
