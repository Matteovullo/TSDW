package com.example;

import java.io.*;
import java.sql.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/wish")
public class WishServlet extends HttpServlet {

    private Connection conn;
    
    public void init() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myDB?user=root&password=root"); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            if (request.getParameter("choise") != null) {

                if (request.getParameter("choise").equals("Si")) {

                    String query = "INSERT INTO wlist (titolo, regista) VALUES (?,?)";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, request.getParameter("titolo"));
                    stmt.setString(2, request.getParameter("regista"));
                    stmt.executeUpdate();

                    out.print("<p>Inserito nella wish list</p>");
                    
                } else if (request.getParameter("choise").equals("No")) {
                    response.sendRedirect("index.jsp");
                }

            } else {

                String query = "SELECT * FROM wlist WHERE titolo=? AND regista=?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, request.getParameter("titolo"));
                stmt.setString(2, request.getParameter("regista"));

                ResultSet res = stmt.executeQuery();

                if (res.next()) {
                    out.print("<h1>Film richiesto: "+res.getString("titolo")+", regista: "+res.getString("regista")+"</h1>");
                } else {
                    out.print(" <p>Il film non è presente nella tua wish list<br>Vuoi aggiungerlo?</p>"
                            +"<form action='/wish' method='post' >"
                            +"<input type='submit' name='choise' value='Si'>"
                            +"<input type='submit' name='choise' value='No'>"
                            +"<input type='hidden' name='titolo' value='"+request.getParameter("titolo")+"'>"
                            +"<input type='hidden' name='regista' value='"+request.getParameter("regista")+"'>"
                            +"</form>");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
