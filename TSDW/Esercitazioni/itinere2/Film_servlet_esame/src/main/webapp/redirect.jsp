<html>
    <body>
        <h1>Films</h1>
        
        <%String titolo = (String)request.getParameter("titolo");%>
        
        <strong><p>Modifica il film <%=titolo%></p></strong>

        <!-- Update -->
        <form action="/servlet" method="post">
            <label for="titolo">Titolo:</label>
            <input type="text" id="titolo" name="titolo"><br>

            <label for="anno">Anno:</label>
            <input type="text" id="anno" name="anno"><br>

            <label for="paese">Paese:</label>
            <input type="text" id="paese" name="paese"><br>

            <label for="regista">Regista:</label>
            <input type="text" id="regista" name="regista"><br><br>

            <button type="submit">Aggiorna</button>
            <input type="hidden" name="titolo" value="<%=titolo%>">
            <input type="hidden" name="action" value="update">
        </form>

        <strong><p>Rimuovi il film <%=titolo%></p></strong>

        <!-- Delete -->
        <form action="/servlet" method="post">
            <button type="submit">Rimuovi</button>
            <input type="hidden" name="titolo" value="<%=titolo%>">
            <input type="hidden" name="action" value="delete">
        </form>

    </body>
</html>