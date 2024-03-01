<html>
    <%
        String nome = (String)request.getParameter("nome");
    %>
    <body>
        <h2>Pagina di aggiornamento</h2>

        <p>Aggiorna corso di <%=nome%>: </p>
        <form action="/servlet" method="POST">
            <label for="descrizione">Descrizione:</label>
            <input type="text" id="descrizione" name="descrizione" required><br>
            <label for="crediti">Crediti:</label>
            <input type="text" id="crediti" name="crediti" required><br><br>
            <button>Aggiorna corso</button>
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="nome" value="<%=nome%>">
        </form><br>

        <p>Rimuovi corso di <%=nome%>: </p>
        <form action="/servlet" method="POST">
            <button>Rimuovi corso</button>
            <input type="hidden" name="action" value="delete">
            <input type="hidden" name="nome" value="<%=nome%>">
        </form>

    </body>
</html>