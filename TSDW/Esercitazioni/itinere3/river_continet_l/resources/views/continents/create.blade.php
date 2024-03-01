<html>
    <body>
        <h2>continents</h2>

        <form action="{{route('continents.store')}}" method="post">
            @csrf
            <input name="name" placeholder="name" required>
            <button>crea</button>
        </form>

    </body>
</html>
