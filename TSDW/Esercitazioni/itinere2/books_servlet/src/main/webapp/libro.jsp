<html>
    <head>
        <%String titolo = (String)request.getParameter("titolo");%>
    </head>
    <body>
        <h2>Books</h2>

        <h4>Modifica il libro <%=titolo%></h4>

        <form action="/servlet" method="post">
            <label for="autore">Autore:</label>
            <input type="text" id="autore" name="autore" required><br>
            
            <label for="prezzo">Prezzo:</label>
            <input type="text" id="prezzo" name="prezzo" required><br>
            <br>
            <button type="submit">Modifica</button>
            <input type="hidden" name="action" value="update"><br>
            <input type="hidden" name="titolo" value="<%=titolo%>"><br>
        </form>
        
        <br>

        <h4>Rimuovi il libro <%=titolo%> </h4>
        <form action="/servlet" method="post">
            <button type="submit">Rimuovi</button>
            <input type="hidden" name="action" value="delete">
            <input type="hidden" name="titolo" value="<%=titolo%>"><br>
        </form>

        <br>

        <a href="index.jsp"> <button>Torna alla home page</button></a>
    </body>
</html>