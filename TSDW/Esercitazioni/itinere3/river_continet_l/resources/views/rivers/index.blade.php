<html>
    <body>
        <h2>river</h2>

        @foreach($rivers as $river)
            Nome: {{$river->name}}
            <a href="{{route('rivers.edit', $river->id)}}">edit</a>
            <a href="{{route('rivers.show', $river->id)}}">show</a>
        @endforeach

        <form action="{{route('rivers.create')}}">
            <button>crea</button>
        </form>
    </body>
</html>
