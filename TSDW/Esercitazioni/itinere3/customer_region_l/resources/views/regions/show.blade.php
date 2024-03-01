<html>
    <body>

        Nome: <p>{{$region->name}}</p>
        Id: <p>{{$region->id}}</p>

        <form action="{{route('regions.destroy', $region->id)}}" method="post">
            @csrf
            @method('delete')
            <button>elimina</button>
        </form>

    </body>
</html>
