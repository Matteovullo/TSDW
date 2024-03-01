<html>
    <body>
        <h2>Magazine</h2>
        
        @if(!$films->isEmpty())
            @foreach($films as $p)
                <form action="/update" method="GET">
                    @csrf

                    <p>ID: {{$p->id_attori}} - Nome: {{$p->nome}} - Giacenza: {{$p->cognome}} - Prezzo: {{$p->data_nascita}}</p>
                    <button>Aggiorna</button>
                    <input type="hidden" name="id_attori" value="{{$p->id_attori}}">
                    <input type="hidden" name="nome" value="{{$p->nome}}">
                    <input type="hidden" name="cognome" value="{{$p->cognome}}">
                    <input type="hidden" name="data_nascita" value="{{$p->data_nascita}}">
                </form>
                <form action="/delete" method="POST">
                    @csrf
                    @method('DELETE')
                    <button>Rimuovi</button>
                    <input type="hidden" name="id_attori" value="{{$p->id_attori}}">
                </form>
            @endforeach
        @else
            <p>Non ci sono prodotti disponibili</p>
        @endif

        <br><a href='/'><button>Torna alla homepage</button></a>
        
    </body>
</html>