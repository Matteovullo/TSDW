package com.example;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

@WebServlet("/servlet")
public class FilmServlet extends HttpServlet {
    private Connection conn;

    public void init() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Film?user=root&password=root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            String query = "SELECT * FROM attori";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                out.print("<p>Nome: " + res.getString("nome")
                        + ", Cognome: " + res.getString("cognome")
                        + ", Data di nascita: " + res.getString("data_nascita") + "</p>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    try {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if ("create".equals(request.getParameter("action"))) {
            String nome = request.getParameter("nome");
            String cognome = request.getParameter("cognome");
            String dataNascita = request.getParameter("data_nascita");

            if (nome != null && cognome != null && dataNascita != null) {
                // Verifica se la data è nel formato corretto (es. yyyy-MM-dd)
                // Esempio di controllo del formato della data
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat.setLenient(false);

                dateFormat.parse(dataNascita);
                // Se la data è nel formato corretto, esegui l'inserimento nel database
                String query = "INSERT INTO attori (nome, cognome, data_nascita) VALUES (?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);

                stmt.setString(1, nome);
                stmt.setString(2, cognome);
                stmt.setString(3, dataNascita);
                stmt.executeUpdate();

                out.print("<html><body>");
                out.print("<h3>Attore inserito correttamente</h3>");
                out.print("</body></html>");
            } else {
                out.print("<html><body>");
                out.print("<h3>Uno o più campi sono nulli</h3>");
                out.print("</body></html>");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } catch (java.text.ParseException e) {
        e.printStackTrace();
    }
}

}
