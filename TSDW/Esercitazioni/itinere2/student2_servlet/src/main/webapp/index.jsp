<html>
    <body>
        <%@page import="java.sql.*"%>
        <%
            Connection conn=null;

            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Database?user=root&password=root");

            String query="SELECT * FROM students";
            PreparedStatement stmt=conn.prepareStatement(query);
            ResultSet res=stmt.executeQuery();

            while(res.next()){
                String corso=res.getString("corso_di_laurea");
                out.print("<p>Nome: "+res.getString("nome")+", cognome: "+res.getString("cognome")+
                ", Corso di laurea: <a href='/servlet?corso_di_laurea="+res.getString("corso_di_laurea")+"'>"+res.getString("corso_di_laurea")+"</a></p>");
            }
        %>
        
        <h2>Inseriamo</h2>
        <form action="/servlet" method="post">
            <label for="nome">Nome: </label>
            <input type="text" id="nome" name="nome">

            <label for="cognome">Cognome: </label>
            <input type="text" id="cognome" name="cognome">

            <label for="corso">Corso di laurea: </label>
            <select name="corso_di_laurea">
                <%
                    query="SELECT codice_corso, nome_corso FROM courses";
                    stmt=conn.prepareStatement(query);
                    res=stmt.executeQuery();

                    while(res.next()){
                        String codice_corso=res.getString("codice_corso");
                        String nome_corso=res.getString("nome_corso");
                        
                %>
                    <option value="<%=codice_corso%>">"<%=nome_corso%>"</option>
                <%
                    }
                %>
            </select>

            
            <input type="hidden" value="create" name="action">
            <button type="submit">inserisci</button>
        </form>
    </body>
</html>
