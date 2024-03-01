<html>

    <body>
        <h2>Benvenuto</h2>

        @foreach($students as $student)
            <p>Nome: {{ $student->nome }}</p>
            <a href="{{ route('students.show', $student->id) }}"><button>dettagli</button></a>
            <a href="{{ route('students.edit', $student->id) }}"><button>modifica</button></a>
        @endforeach
        <br>

        <a href="{{ route('students.create') }}"><button>crea</button></a>
    </body>

</html>
