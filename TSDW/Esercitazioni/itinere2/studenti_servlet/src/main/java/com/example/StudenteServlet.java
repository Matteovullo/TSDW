package com.example;

import java.io.*;
import java.sql.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/servlet")
public class StudenteServlet extends HttpServlet{
    
    Connection conn = null;

    public void init() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uni?user=root&password=root");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();

            out.print("<link rel=\"stylesheet\" type=\"text/css\" href=\"./css/style.css\">");
            String corso_ = (String)req.getParameter("corso");
            String nome = (String)req.getParameter("nome");
            String matricola = (String)req.getParameter("matricola");

            String query = "SELECT * FROM courses WHERE codice_corso=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, corso_);
            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                out.print("<h2>Studente "+nome+" :</h2>");
                do {
                    out.print("<p>Codice corso: "+res.getString("codice_corso")+
                              ", descrizione: "+res.getString("descrizione")+
                              ", crediti: "+res.getString("crediti")+"</p>");
                } while (res.next());
            } else {
                out.print("Nessun corso di laurea assocciato allo studente "+nome+"");
            }

            String corso = getNomeCorso(corso_);

            out.print("<br><h3>Cambia corso di laurea</h3>"+
                        "<form action='/servlet' method='POST'>"+
                            "<label for='corso'>Corso di laurea: </label>"+
                            "<input type='text' id='corso' name='corso'><br><br>"+
                            "<button type='submit'>Aggiorna corso</button>"+
                            "<input type='hidden' name='action' value='update'>"+
                            "<input type='hidden' name='matricola' value='"+matricola+"'>"+
                            "<input type='hidden' name='corso' value='"+corso+"'>"+
                       "</form><br>");

            out.print("<br><h3>Rimuovi studente "+nome+"</h3>"+
            "<form action='/servlet' method='POST'>"+
                "<button type='submit'>Rimuovi studente</button>"+
                "<input type='hidden' name='action' value='delete'>"+
                "<input type='hidden' name='matricola' value='"+matricola+"'>"+
            "</form><br>");

            out.print("<a href='/index.jsp'><button>Torna alla home page</button></a>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.print("<link rel=\"stylesheet\" type=\"text/css\" href=\"./css/style.css\">");

            if (req.getParameter("action") != null) {
                
                if (req.getParameter("action").equals("create")) {

                    String nome = req.getParameter("nome");
                    String cognome = req.getParameter("cognome");
                    String corso = getIdCorso(req.getParameter("corso"));

                    if (!corso.startsWith("Errore!")) {

                        String query = "INSERT INTO students (nome, cognome, corso_di_laurea) VALUES (?,?,?)";
                        PreparedStatement stmt = conn.prepareStatement(query);
                        stmt.setString(1, nome);
                        stmt.setString(2, cognome);
                        stmt.setString(3, corso);
    
                        stmt.executeUpdate();
                        out.print("<h3>Studente "+nome+" inserito con successo</h3>");

                    } else {
                        out.print("<h3>"+corso+"</h3>");
                    }
                }

                if (req.getParameter("action").equals("update")) {
                    String matricola = req.getParameter("matricola");
                    String corso = getIdCorso(req.getParameter("corso"));

                    String query = "UPDATE students SET corso_di_laurea=? WHERE matricola=?";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, corso);
                    stmt.setString(2, matricola);

                    stmt.executeUpdate();
                    out.print("<h3>Corso di laurea aggiornato con successo</h3>");
                }

                if (req.getParameter("action").equals("delete")) {
                    String matricola = req.getParameter("matricola");

                    String query = "DELETE FROM students WHERE matricola = ?";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, matricola);

                    stmt.executeUpdate();
                    out.print("<h3>Studente rimosso con successo</h3>");
                }
            }
            out.print("<a href='/index.jsp'><button>Torna alla home page</button></a>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String getNomeCorso(String id) {
        try {
            String query_ = "SELECT nome_corso FROM courses WHERE codice_corso=?";
            PreparedStatement stmt_ = conn.prepareStatement(query_);
            stmt_.setString(1, id);
            ResultSet res = stmt_.executeQuery();

            if (res.next())
                return res.getString("nome_corso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Errore! Corso di laurea non disponibile";
    }

    String getIdCorso(String nome) {
        try {
            String query_ = "SELECT codice_corso FROM courses WHERE nome_corso=?";
            PreparedStatement stmt_ = conn.prepareStatement(query_);
            stmt_.setString(1, nome);
            ResultSet res = stmt_.executeQuery();

            if (res.next())
                return res.getString("codice_corso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Errore! Corso di laurea non disponibile";
    }
}