<html>

    <body>
        <h2>Order</h2>

        @foreach($orders as $order)
            Tavolo: {{ $order->tavolo }}
            <a href="{{ route('orders.show', $order->id) }}">dettagli</a>
            <a href="{{ route('orders.edit', $order->id) }}">modifica</a>
            <br>
        @endforeach

        <form action="{{ route('orders.create') }}" action="post">
            <button>crea</button>
        </form>

    </body>

</html>
