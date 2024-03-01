<html>
    <body>
        <h2>continents</h2>

        Nome: {{$continent->name}}
        Id: {{$continent->id}}

        <form action="{{route('continents.destroy', $continent->id)}}" method="post">
            @csrf
            @method('delete')
            <button>elimina</button>
        </form>
    </body>
</html>
