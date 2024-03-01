<html>
    <body>
        <h2>continents</h2>

        <form action="{{route('continents.update', $continent->id)}}" method="post">
            @csrf
            @method('put')
            <input name="name" placeholder="name" required>
            <button>crea</button>
        </form>

    </body>
</html>
