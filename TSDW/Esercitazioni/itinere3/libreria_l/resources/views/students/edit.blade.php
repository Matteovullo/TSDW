<html>

    <body>
        <h2>Benvenuto</h2>

        <form action="{{ route('students.update', $student->id) }}" method="post">
            @csrf
            @method('put')

            Nome: <input type="text" name="nome" required>
            Cognome: <input type="text" name="cognome" required>
            Eta: <input type="text" name="eta" required>
            <button>modifica</button>
        </form>

    </body>

</html>
