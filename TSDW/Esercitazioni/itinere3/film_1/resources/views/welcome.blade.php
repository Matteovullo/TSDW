<html>
    <body>
        <h2>Film<h2>

        <form action="/read" method="get">
            <button>Vedi tutti:</button>
        </form>

        <form action="/insert" method="post">
            @csrf
            Titolo: <input type="text" id="titolo" name="titolo" required>
            Anno: <input type="text" id="anno" name="anno" required>
            Paese: <input type="text" id="paese" name="paese" required>
            Regista: <input type="text" id="regista" name="regista" required>
            
            <button>Vedi tutti:</button>
        </form>
    </body> 
</html>