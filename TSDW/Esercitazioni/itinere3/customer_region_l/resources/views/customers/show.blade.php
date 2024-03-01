<html>
    <body>

        Nome: <p>{{$customer->name}}</p>
        Job: <p>{{$customer->job}}</p>
        Img: <p>{{$customer->img}}</p>
        Img_cliccabile: <img src={{$customer->img}}>
        Prezzo: <p>{{$customer->price}}</p>
        id_region: <p>{{$customer->id_region}}</p>

        <form action="{{route('customers.destroy', $customer->id)}}" method="post">
            @csrf
            @method('delete')
            <button>elimina</button>
        </form>

    </body>
</html>
