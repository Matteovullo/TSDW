<html>
    <body>
        @if(!$films->isEmpty())
            @foreach($films as $f)
                <form action="/update" method="get">
                    <p>ID: {{ $f->id }} - {{ $f->titolo }} - {{ $f->anno }} - {{ $f->paese }} - {{ $f->regista }}</p>
                    <button>Aggiorna</button>
                    <input type="hidden" id="titolo" name="{{ $f->titolo }}" required>
                    <input type="hidden" id="titolo" name="{{ $f->titolo }}" required>
                    <input type="hidden" id="anno" name="{{ $f->anno }}" required>
                    <input type="hidden" id="paese" name="{{ $f->paese }}" required>
                    <input type="hidden" id="regista" name="{{ $f->regista }}" required>
                </form>

                <form action="/delete" method="post">
                    @csrf
                    @method('DELETE')
                    <button>Aggiorna</button>
                    <input type="hidden" id="titolo" name="{{ $f->titolo }}" required>
                </form>
            @endeach
        @else
            <p> Non ci sono film </p>
        @endif
    </body>
</html>