<html>

    <body>
        <h2>Students</h2>
        <form action="/read" method="get">
            <button>vedi tutti</button>
        </form>
        <br>

        <h2>Inserimento</h2>
        <form action="/insert" method="post">
            @csrf
            name: <input type="text" name="name" required>
            age: <input type="text" name="age" required>
            <button>inserisci</button>
        </form>
    </body>

</html>
