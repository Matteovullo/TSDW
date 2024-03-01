<html>
    <body>
        <h2>rivers</h2>

        <form action="{{route('rivers.store')}}" method="post">
            @csrf
            <input name="name" placeholder="name" required>
            <input name="lenght" placeholder="lenght" required>
            continent_id:
            <select name="continent_id">
                @foreach($continents as $c)
                <option value="{{ $c->id }}">{{ $c->name }}</option>
                @endforeach
            </select>
            <button>crea</button>
        </form>

    </body>
</html>
