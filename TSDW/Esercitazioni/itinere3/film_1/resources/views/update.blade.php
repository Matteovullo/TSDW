<html>
    <body>
        <h3>Magazine</h3>

        <form action="/update" method="POST">
            @csrf
            @method('PUT')
            
            Titolo: <input type="hidden" id="titolo" name="{{ $_GET['titolo'] }}" required>
            Anno: <input type="hidden" id="anno" name="{{ $_GET['anno'] }}" required>
            Paese: <input type="hidden" id="paese" name="{{ $_GET['paese'] }}" required>
            Regista: <input type="hidden" id="regista" name="{{ $_GET['regista'] }}" required>
            
            <button type="submit">Salva Modifiche</button>
            <input type="hidden" name="id" value="{{$_GET['id']}}">
        </form>

        <br><a href='/'><button>Torna alla home</button></a>
    </body>
</html>