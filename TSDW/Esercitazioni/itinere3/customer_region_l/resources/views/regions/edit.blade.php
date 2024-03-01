<html>
    <body>
        <form action="{{route('regions.update', $region->id)}}" method="post">
            @csrf
            @method('put')
            <input name="name" placeholder="name" required>
            <button>crea</button>
        </form>
    </body>
</html>
