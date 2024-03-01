<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="./css/style.css">
        <title>Benvenuto</title>
    </head>
    <body>
        <h1>Benvenuto!</h1>
        <strong><p>Benvenuto al nostro sito.</p></strong>

        <form action="MyServlet" method="post">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
            <br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            <br>
            <button type="submit">Logout</button>
        </form>
    </body>
</html>