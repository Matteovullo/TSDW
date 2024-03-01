<html>

    <body>
        <h2>Order</h2>

        <form action="{{ route('orders.update', $order->id) }}" method="post">
            @csrf
            @method('put')

            Tavolo: <input type="text" name="tavolo" required>
            Prezzo: <input type="text" name="prezzo" required>
            Cliente:
            <select name="client" required>
                @foreach($client as $client)
                    <option value="{{ $client->id }}">{{ $client->nome }}</option>
                @endforeach
            </select>
            <button>modifica</button>
        </form>
    </body>

</html>
