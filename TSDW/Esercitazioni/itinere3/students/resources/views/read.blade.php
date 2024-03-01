<html>
    <body>
        <h1>Students<h1>
        @if(!$students->isEmpty())
            @foreach($students as $s)
            <form action="/form" method="post" >
                @csrf
                <p>Id: {{ $s->id }} - name: {{ $s->name }} - age: {{ $s->age }}</p>
                <input type="hidden" name="name" value="{{ $s->name }}" required>
                <input type="hidden" name="age" value="{{ $s->age }}" required>
                <input type="submit" name="action" value="update">
                <input type="submit" name="action" value="delete">
                <input type="hidden" name="id" value="{{ $s->id }}">
            </form>
            @endforeach
        @endif
    </body>

</html>
