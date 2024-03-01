<html>

    <body>
        <h2>Client</h2>

        @foreach($clients as $client)
            Nome: {{ $client->nome }}
            <a href="{{ route('clients.show', $client->id) }}">dettagli</a>
            <a href="{{ route('clients.edit', $client->id) }}">modifica</a>
            <br>
        @endforeach

        <form action="{{ route('clients.create') }}" action="post">
            <button>crea</button>
        </form>

    </body>

</html>
