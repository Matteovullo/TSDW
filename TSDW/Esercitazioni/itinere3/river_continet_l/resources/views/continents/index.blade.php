<html>
    <body>
        <h2>continents</h2>

        @foreach($continents as $continent)
            Nome: {{$continent->name}}
            <a href="{{route('continents.edit', $continent->id)}}">edit</a>
            <a href="{{route('continents.show', $continent->id)}}">show</a>
        @endforeach

        <form action="{{route('continents.create')}}">
            <button>crea</button>
        </form>
    </body>
</html>
