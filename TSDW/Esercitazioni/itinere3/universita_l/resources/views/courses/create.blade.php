<html>
    <body>
        <form action="{{ route('course.store')}}" method="post">
            @csrf

            Nome: <input type="text" name="nome" required>
            Cfu: <input type="text" name="cfu" required>
            Dipartimento: <input type="text" name="dipartimento" required>
        </form>
    </body>
</html>
