<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Index</title>
</head>
<body>
    <h1>Index</h1>

        <% 
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        String dbURL = "jdbc:mysql://localhost/myDB";
        String dbUsername = "root";
        String dbPassword = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

            String query = "SELECT titolo, regista FROM flist ORDER BY RAND() LIMIT 1";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            if (rs.next()) {
                String titoloCasuale = rs.getString("titolo");
                String registaCasuale = rs.getString("regista");
        %>

        <h2>Film Consigliato:</h2>
        <p><strong>Titolo:</strong> <%= titoloCasuale %></p>
        <p><strong>Regista:</strong> <%= registaCasuale %></p>

        <% 
                } else {
        %>

        <p>Nessun film trovato</p>

        <% 
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (stmt != null) stmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        %>


        <h1>Cerca un film:</h1>
        <form action="/servlet" method="post">
            <label for="titolo">Titolo: </label>
            <input type="text" id="titolo" name="titolo">
            <br>

            <label for="regista">Regista: </label>
            <input type="text" id="regista" name="regista">
            <br>
            <button type="submit">cerca</button>
            <input type="hidden" name="action" value="read">
        </form>

        <h1>Lista dei desideri: </h1>
        <form action="/servlet" method="get">
            <button type="submit">visualizza</button>
            <input type="hidden" name="action" value="read_wish">
        </form>

        <h1>Svuota lista dei desideri: </h1>
        <form action="/servlet" method="post">
            <button type="submit">svuota</button>
            <input type="hidden" name="action" value="delete">
        </form>
    </body>
</html>