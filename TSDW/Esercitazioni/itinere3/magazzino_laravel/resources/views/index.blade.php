<html>
    <body>
        <h2>Magazzino</h2>

        <form action="/read" method="get">
            <button>Vedi tutti</button>
        </form>

        <h2>Inserimento</h2>
        <form action="/insert" method="POST">
            @csrf 
            Nome:<input type="text" name="nome_prodotto" required><br>
            Giacenza:<input type="text" name="giacenza" required><br>
            Prezzo:<input type="text" name="prezzo" required><br><br>
            <button>Inserisci</button>
        </form>
        
    </body>
</html>