<html>
    <body>
        <h2>Farmacia</h2>
        <form action="/farmacia" method="get">
            <input type="hidden" name="action" value="read">
            <button type="submit">Vedi tutti</button>
        </form>

        <h2>Farmacia2</h2>
        <form action="/farmacia" method="post">
        <%@page import="java.sql.*"%>

        <%
        Connection conn=null;
        conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Database?user=root&password=root");

        String query="SELECT * FROM farmacia";
        PreparedStatement stmt=conn.prepareStatement(query);
        ResultSet res=stmt.executeQuery();
            
        %>
        <select name="tutti">
            <% 
                while(res.next()) {
                    String nome = res.getString("nome");
            %>
                    <option value="<%= nome %>"><%= nome %></option>
            <% 
                } 
            %>
        </select>        
        
        <input type="hidden" name="action" value="read2">
        <button type="submit">Vedi</button>
        </form>
        
        <h2>Inserimento farmaco</h2>
        <form action="/farmacia" method="post">
            Nome: <input type="text" name="nome" id="nome">
            Principio: <input type="text" name="principio" id="principio">
            Forma: <input type="text" name="forma" id="forma">
            Dosaggio: <input type="text" name="dosaggio" id="dosaggio">
            Scadenza: <input type="date" name="scadenza" id="scadenza">

            <input type="hidden" name="action" value="create">
            <button type="submit">inserisci</button>
        </form>

        <h2>Eliminazione farmaco</h2>
        <form action="/farmacia" method="post">
            Nome: <input type="text" name="nome" id="nome">
            <input type="hidden" name="action" value="delete">
            <button type="submit">elimina</button>
        </form>

        <h2>Aggiornamento farmaco</h2>
        <form action="/farmacia" method="post">
            Nome: <input type="text" name="nome" id="nome">
            Scadenza: <input type="date" name="scadenza" id="scadenza">
            <input type="hidden" name="action" value="update">
            <button type="submit">aggiorna</button>
        </form>
    </body>
</html>
