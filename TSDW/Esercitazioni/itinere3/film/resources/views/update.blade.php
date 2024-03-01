<html>
    <body>
        <h3>Magazine</h3>

        <form action="/update" method="POST">
            @csrf
            @method('PUT')
            
            Nome: <input type="text" name="nome" value="{{$_GET['nome']}}"><br>
            Cognome: <input type="text" name="cognome" value="{{$_GET['cognome']}}"><br>
            Data: <input type="date" name="data_nascita" value="{{$_GET['data_nascita']}}"><br><br>
            
            <button type="submit">Salva Modifiche</button>
            <input type="hidden" name="id_attori" value="{{$_GET['id_attori']}}">
        </form>

        <br><a href='/'><button>Torna alla home</button></a>
    </body>
</html>
