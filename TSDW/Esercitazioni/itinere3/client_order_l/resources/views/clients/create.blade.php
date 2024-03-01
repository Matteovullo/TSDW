<html>

    <body>
        <h2>Client</h2>

        <form action="{{ route('owners.store') }}" method="post">
            @csrf

            Nome: <input type="text" name="nome" required>
            Eta: <input type="text" name="eta" required>
            <button>crea</button>
        </form>
    </body>

</html>
