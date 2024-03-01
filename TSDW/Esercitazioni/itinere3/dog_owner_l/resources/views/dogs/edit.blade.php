<html>
    <body>
        <h2>owners</h2>

        <form action="{{ route('dogs.update', $dog->id) }}" method="post">
            @csrf
            @method('put')
            <input name="nome" placeholder="nome" required>
            <input name="eta" placeholder="eta" required>
            owners:
            <select name="owner" required>
                @foreach($owners as $owner)
                    <option value="{{ $owner->id }}">{{ $owner->nome }}</option>
                @endforeach
            </select>
            <button>modifica</button>
        </form>

    </body>
</html>
