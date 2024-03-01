package com.example;

import java.io.*;
import java.sql.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/servlet")
public class FilmServlet extends HttpServlet {

    Connection conn;

    public void init() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cinema?user=root&password=root");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String query="SELECT titolo, regista FROM flist ORDER BY RAND() LIMIT 1";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet res  = stmt.executeQuery();

            out.print("<h1>Film consigliato:</h1>");

            if (res.next()) {
                out.print("<p>Titolo: "+res.getString("titolo")+", Regista: "+res.getString("regista")+"</p>");
            }else {
                out.print("<p>Nessun film trovato</p>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            readAllFilms(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            if (request.getParameter("action").equals("create")) {
                createFilm(out, request.getParameter("titolo"), request.getParameter("anno"),request.getParameter("paese"), request.getParameter("regista"));
            }
            
            else if (request.getParameter("action").equals("update")) 
                updateFilm(out, request.getParameter("titolo"), request.getParameter("anno"),request.getParameter("paese"), request.getParameter("regista"));
            
            else if (request.getParameter("action").equals("delete")) 
                deleteFilm(out, request.getParameter("titolo"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void readAllFilms(PrintWriter out) {
        try {
            String query = "SELECT * FROM films";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet res  = stmt.executeQuery();

            out.print("<html><body>");
            out.print("<h2>Films</h2>");
            out.print("<h4>Film presenti :</h4>");

            while (res.next()) {
                String titolo = res.getString("titolo");
                String anno = res.getString("anno");
                String paese = res.getString("paese");
                String regista = res.getString("regista");
                out.print("<p>Titolo: <a href='redirect.jsp?titolo="+titolo+"'>"+titolo+"</a>, Anno: "+anno+", Paese: "+paese+", Regista: "+regista+"</p>");
            }   

            out.print("<a href='index.jsp'><button>Torna alla home page</button></a>");
            out.print("</body></html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void createFilm(PrintWriter out, String titolo, String anno, String paese, String regista) {
        try {
            out.print("<html><body>");
            out.print("<h2>Films</h2>");

            String query = "INSERT INTO films (titolo, anno, paese, regista) VALUES (?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, titolo);
            stmt.setString(2, anno);
            stmt.setString(3, paese);
            stmt.setString(4, regista);
            stmt.executeUpdate();

            out.print("<h4>Film inserito correttamente</h4>");
            out.print("<a href='index.jsp'><button>Torna alla home page</button></a>");
            out.print("</body></html>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void updateFilm(PrintWriter out, String titolo, String anno, String paese, String regista) {
        try {
            out.print("<html><body>");
            out.print("<h2>Films</h2>");

            String query = "UPDATE films SET anno=?, paese=?, regista=? WHERE titolo=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, anno);
            stmt.setString(2, paese);
            stmt.setString(3, regista);
            stmt.setString(4, titolo);
            stmt.executeUpdate();

            out.print("<h4>Film aggiornato correttamente</h4>");
            out.print("<a href='index.jsp'><button>Torna alla home page</button></a>");
            out.print("</body></html>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void deleteFilm(PrintWriter out, String titolo) {
        try {
            out.print("<html><body>");
            out.print("<h2>Films</h2>");

            String query = "DELETE FROM films WHERE titolo=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, titolo);
            stmt.executeUpdate();

            out.print("<h4>Film rimosso correttamente</h4>");
            out.print("<a href='index.jsp'><button>Torna alla home page</button></a>");
            out.print("</body></html>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



