<html>
    <body>
        <h2>dogs</h2>

        @foreach($dogs as $dog)
            Nome: {{ $dog->nome }}
            <a href="{{ route('dogs.show', $dog->id)}}">dettagli</a>
            <a href="{{ route('dogs.edit', $dog->id)}}">modifica</a>
        @endforeach
        <br>

        <form action="{{ route('dogs.create') }}">
            <button>crea</button>
        </form>
    </body>
</html>
