<html>
    <body>
        <form action="{{ route('course.update', $course->id)}}" method="post">
            @csrf
            @method('put')

            Nome: <input type="text" name="nome" required>
            Cfu: <input type="text" name="cfu" required>
            Dipartimento: <input type="text" name="dipartimento" required>
            <button>modifica</button>
        </form>
    </body>
</html>
