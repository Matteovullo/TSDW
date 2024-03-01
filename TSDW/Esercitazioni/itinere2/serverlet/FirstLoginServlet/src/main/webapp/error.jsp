<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>

<!DOCTYPE html>
<html>
<head>
    <title>Errore</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <h1>Errore!</h1>
    <br>
    <p><%= session.getAttribute("message") %></p>
    <br>
    <a href="index.jsp"><button id="logout">Torna al login</button></a>
</body>
</html>