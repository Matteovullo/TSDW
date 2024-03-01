<html>
    <body>
        <form action="{{route('customers.store')}}" method="post">
            @csrf
            <input name="name" placeholder="name" required>
            <input name="job" placeholder="job" required>
            <input name="img" placeholder="img" required>
            <input name="price" placeholder="price" required>
            regione:
            <select name="id_region">
                @foreach($regions as $region)
                    <option value="{{$region->id}}">{{$region->name}}</option>
                @endforeach
            </select>
            <button>crea</button>
        </form>
    </body>
</html>
