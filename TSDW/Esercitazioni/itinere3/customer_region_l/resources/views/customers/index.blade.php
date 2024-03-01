<html>
    <body>

        @foreach($customers as $customer)
        Nome: <p>{{$customer->name}}</p>
        <a href="{{route('customers.show', $customer->id)}}"><button>show</button></a>
        <a href="{{route('customers.edit', $customer->id)}}"><button>edit</button></a>
        @endforeach

        <form action="{{route('customers.create')}}">
            <button>crea</button>
        </form>

        <form action="{{route('customers.decrese')}}" method="post">
            @csrf
            <button>dimezza</button>
        </form>

    </body>
</html>
