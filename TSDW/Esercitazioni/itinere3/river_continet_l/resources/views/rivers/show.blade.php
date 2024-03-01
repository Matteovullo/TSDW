<html>
    <body>
        <h2>river</h2>

        Nome: {{$river->name}}
        Length: {{$river->length}}
        Continent_id: {{$river->continent_id}}

        <form action="{{route('rivers.destroy', $river->name)}}" method="post">
            <button>crea</button>
        </form>
    </body>
</html>
