<html>
    <body>
        <h2>dogs</h2>

        <p>Nome: {{ $dog->nome }}</p>
        <p>Eta: {{ $dog->eta }}</p>
        <p>Id: {{ $dog->id }}</p>
        <p>Owner: {{ $dog->owner }}</p>

        <form action="{{ route('dogs.destroy', $dog->id) }}" method="post">
            @csrf
            @method('delete')
            <button>elimina</button>
        </form>
    </body>
</html>
