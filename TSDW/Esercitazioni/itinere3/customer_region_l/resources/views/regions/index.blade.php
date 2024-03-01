<html>
    <body>

        @foreach($regions as $region)
        Nome: <p>{{$region->name}}</p>
        <a href="{{route('regions.show', $region->id)}}"><button>show</button></a>
        <a href="{{route('regions.edit', $region->id)}}"><button>edit</button></a>
        @endforeach

        <form action="{{route('regions.create')}}">
            <button>crea</button>
        </form>

    </body>
</html>
