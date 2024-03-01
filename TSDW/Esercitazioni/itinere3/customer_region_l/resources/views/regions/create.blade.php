<html>
    <body>
        <form action="{{route('regions.store')}}" method="post">
            @csrf
            <input name="name" placeholder="name" required>
            <button>crea</button>
        </form>
    </body>
</html>
