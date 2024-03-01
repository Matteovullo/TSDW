<html>

    <body>
        <h2>Order</h2>

        <p>Tavolo: {{ $order->tavolo }}</p>
        <p>Prezzo: {{ $order->prezzo }}</p>
        <p>Id: {{ $order->id }}</p>
        <p>Cliente: {{ $order->client }}</p>

        <form action="{{ route('orders.destroy', $order->id) }}" method="POST">
            @csrf
            @method('DELETE')
            <button type="submit">Elimina</button>
        </form>
    </body>

</html>
