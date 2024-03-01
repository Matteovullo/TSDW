<html>

    <body>
        <h2>Client</h2>

        <form action="{{ route('clients.update', $client->id) }}" method="post">
            @csrf
            @method('put')

            Nome: <input type="text" name="nome" required>
            Eta: <input type="text" name="eta" required>
            <button>modifica</button>
        </form>
    </body>

</html>
