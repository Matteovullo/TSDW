<html>

    <body>
        <h1>Courses</h1>
            <p>Nome: {{ $course->nome }}</p>
            <p>Cfu: {{ $course->cfu }}</p>
            <p>Dipartimento: {{ $course->dipartimento }}</p>

        <form action="{{ rout('courses.destory', $course->nome)}}" method="post">
            @csrf
            @method('delete')
            <button>elimina</button>
        </form>
    </body>

</html>
