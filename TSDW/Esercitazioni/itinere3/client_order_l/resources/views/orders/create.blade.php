<html>

    <body>
        <h2>Order</h2>

        <form action="{{ route('orders.store') }}" method="post">
            @csrf

            Tavolo: <input type="text" name="tavolo" required>
            Prezzo: <input type="text" name="prezzo" required>
            Cliente:
            <select name="client" required>
                @foreach($clients as $client)
                    <option value="{{ $client->id }}">{{ $client->nome }}</option>
                @endforeach
            </select>
            <button>crea</button>
        </form>
    </body>

</html>
