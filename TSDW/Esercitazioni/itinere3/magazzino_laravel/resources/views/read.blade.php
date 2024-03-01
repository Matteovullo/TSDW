<html>
    <body>
        <h2>Magazzino</h2>
        @if(!$prodotti->isEmpty())
            @foreach($prodotti as $p)
                <form method="/update" action="get">
                    @csrf
                    <p>ID: {{ $p->id }} nome: {{ $p->nome_prodotto }} giacenza: {{ $p->giacenza }} prezzo: {{ $p->prezzo }}</p>
                    <button>Aggiorna</button>
                    <input type="hidden" name="id" value="{{ $_GET['nome_prodotto'] }}"><br>
                    <input type="hidden" name="nome_prodotto" value="{{ $_GET['giacenza'] }}"><br>
                    <input type="hidden" name="giacenza" value="{{ $_GET['prezzo'] }}"><br>
                </form>
            @endforeach
        @else
            <p>Non ci sono prodotti disponibili</p>
        @endif
    </body>
</html>