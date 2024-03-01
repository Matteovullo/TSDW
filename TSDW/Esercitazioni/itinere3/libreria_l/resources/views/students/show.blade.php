<html>

    <body>
        <h2>Benvenuto</h2>

        <p>Nome: {{ $student->nome }}</p>
        <p>Cognome: {{ $student->cognome }}</p>
        <p>Eta: {{ $student->eta }}</p>

        <form action="{{ route('students.destroy', $student->id) }}" method="post">
            @csrf
            @method('delete')

            <button>elimina</button>
        </form>
    </body>

</html>
