<html>
    <body>
        <h1>Authors</h1>

        @foreach($authors as $author)
        <form>
            <p>Name: {{$author->nome}}</p>
            <button formaction="{{route('authors.show', $author->id)}}" method="GET">Show</button>
            <button formaction="{{route('authors.edit', $author->id)}}" method="GET">Edit</button>
        </form>
        @endforeach

        <br>

        <form action="{{route('authors.create')}}" method="GET">
            <button>Create new client</button>
        </form>

    </body>
</html>
