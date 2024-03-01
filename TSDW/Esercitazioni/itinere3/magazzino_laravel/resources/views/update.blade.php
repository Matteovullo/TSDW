<html>
    <body>
        <form action="/update" method="put">
            @csrf
            @method('PUT')

            Nome: <input type="hidden" name="id" value="{{ $_GET['nome_prodotto'] }}"><br>
            Giacenza: <input type="text" name="nome_prodotto" value="{{ $_GET['giacenza'] }}"><br>
            Prezzo: <input type="text" name="giacenza" value="{{ $_GET['prezzo'] }}"><br>

            <button>Salva</button>
            <input type="hidden" name="id">
        </form>
    </body>
</html>