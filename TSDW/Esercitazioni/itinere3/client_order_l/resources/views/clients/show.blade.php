<html>

    <body>
        <h2>Client</h2>

        <p>Nome: {{ $client->nome }}</p>
        <p>Eta: {{ $client->eta }}</p>
        <p>ID: {{ $client->id }}</p>

        <form action="{{ route('clients.destroy', $client->id) }}" method="POST">
            @csrf
            @method('DELETE')
            <button type="submit">Elimina</button>
        </form>
    </body>

</html>


