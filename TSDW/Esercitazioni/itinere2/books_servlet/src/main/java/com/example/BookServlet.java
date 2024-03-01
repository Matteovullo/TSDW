package com.example;

import java.io.*;
import java.sql.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/servlet")
public class BookServlet extends HttpServlet {

    Connection conn = null;
    final String connectionString = "jdbc:mysql://localhost:3306/Library?user=root&password=root";

    public void init() {
        try {
            conn = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            String query = "SELECT * FROM books";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet res = stmt.executeQuery();
            
            out.print ("<html><body>");
            out.print ("<h2>Books</h2>");
            out.print ("<h4>Libri presenti: </h4>");

            while(res.next()) {
                out.print ("<p>Isbn: <a href='libro.jsp?titolo=" + res.getString("titolo")+ "'>" + res.getString("isbn") + "</a>" +
                           ", Titolo: "   + res.getString("titolo") +
                           ", Autore: "   + res.getString("autore") +
                           ", Prezzo: "   + res.getString("prezzo") + "</p>");
            }

            out.print("<a href='index.jsp'><button>Torna alla home page</button></a>");
            out.print ("</body></html>");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            if (request.getParameter("action").equals("create")) {
                String query = "INSERT INTO books (Titolo, Autore, Prezzo) VALUES (?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, request.getParameter("titolo"));
                stmt.setString(2, request.getParameter("autore"));
                stmt.setString(3, request.getParameter("prezzo"));
                stmt.executeUpdate();
                
                out.print ("<html><body>");
                out.print ("<h2>Books</h2>");
                out.print ("<h4>Libro inserito correttamente </h4>");
                out.print("<a href='index.jsp'><button>Torna alla home page</button></a>");
                out.print ("</body></html>");
            }  

            else if (request.getParameter("action").equals("update")) {
                String query = "UPDATE books SET autore = ?, prezzo = ? WHERE titolo = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, request.getParameter("autore"));
                stmt.setString(2, request.getParameter("prezzo"));
                stmt.setString(3, request.getParameter("titolo"));
                stmt.executeUpdate();
                
                out.print ("<html><body>");
                out.print ("<h2>Books</h2>");
                out.print ("<h4>Libro aggiornato correttamente </h4>");
                out.print("<a href='index.jsp'><button>Torna alla home page</button></a>");
                out.print ("</body></html>");
            }

            else if (request.getParameter("action").equals("delete")) {
                String query = "DELETE FROM books WHERE titolo = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, request.getParameter("titolo"));
                stmt.executeUpdate();
                
                out.print ("<html><body>");
                out.print ("<h2>Books</h2>");
                out.print ("<h4>Libro rimosso correttamente </h4>");
                out.print("<a href='index.jsp'><button>Torna alla home page</button></a>");
                out.print ("</body></html>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
