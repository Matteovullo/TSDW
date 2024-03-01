<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <strong><p>Benvenuto al login</p></strong>

        <form action="/LoginServlet" method="post">
            <label for="username">Username:</label>
            <input type="username" id="username" name="username" required>
            <br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            <br>
            <button type="submit">Login</button>
        </form> 

    </body>
</html>