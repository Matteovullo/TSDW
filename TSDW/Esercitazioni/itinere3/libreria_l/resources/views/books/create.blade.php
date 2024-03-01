<html>

    <body>
        <h2>Benvenuto</h2>

        <form action="{{ route('books.store') }}" method="post">
            @csrf

            Nome: <input type="text" name="nome" required>
            Volume: <input type="text" name="volume" required>
            Studente:
            <select name="student" required>
                @foreach($students as $student)
                    <option value="{{ $student->id }}">{{ $student->nome }}</option>
                @endforeach
            </select>
            <button>crea</button>
        </form>

    </body>

</html>
