<html>

<body>
    <h1>Team creazione</h1>

    <form action="{{ route('teams.store') }}" method="post">
        @csrf

        Nome: <input type="text" name="nome" required>
        Stadio: <input type="text" name="stadio" required>
        Anno di fondazione: <input type="number" name="anno_fondazione" required>
        <button type="submit">crea</button>
    </form>
</body>

</html>

