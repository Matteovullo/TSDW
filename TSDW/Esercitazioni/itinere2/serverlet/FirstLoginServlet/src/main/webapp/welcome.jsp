<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>

<!DOCTYPE html>
<html>
<head>
    <title>Benvenuto</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <h1>Benvenuto!</h1>
    <br>
    <p>Ti chiami <%= session.getAttribute("username") %></p>
    <br>
    <a href="index.jsp"><button id="logout">Logout</button></a>
</body>
</html>