<html>

    <body>
        <h2>Aggiorna</h2>
        <form action="/update" method="post">
            @csrf
            name: <input type="text" name="name" value="{{ $students->name }}" required>
            age: <input type="text" name="age" value="{{ $students->age }}" required>
            <input type="hidden" name="id" value="{{ $students->id }}">
            <button>aggiorna</button>
        </form>
    </body>

</html>
