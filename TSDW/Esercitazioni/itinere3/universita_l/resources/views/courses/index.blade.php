<html>

    <body>
        <h1>Courses</h1>

        @foreach($courses ad $course)
            <p>
                Nome: {{ $course->nome }}
                <a href="{{ route('course.show', $course->id)}}"><button>mostra</button></a>
                <a href="{{ route('course.edit', $course->id)}}"><button>edit</button></a>
            </p>
        @endforeach
    </body>

</html>
