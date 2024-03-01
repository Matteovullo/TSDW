<html>

    <body>
        <h2>Benvenuto</h2>

        <form action="{{ route('students.store') }}" method="post">
            @csrf

            Nome: <input type="text" name="nome" required>
            Cognome: <input type="text" name="cognome" required>
            Eta: <input type="text" name="eta" required>
            <button>crea</button>
        </form>

        <a href="{{ route('students.create') }}"><button>crea</button></a>
    </body>

</html>
