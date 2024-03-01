package com.example;

import java.io.*;
import java.sql.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/servlet")
public class CorsiServlet extends HttpServlet{
    
    Connection conn=null;

    public void init(){
        try{
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/uni?user=root&password=root");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response){
        try{
            response.setContentType("text/html");
            PrintWriter out=response.getWriter();

            if(request.getParameter("action").equals("read")){
                String query="SELECT * FROM courses";
                PreparedStatement stmt=conn.prepareStatement(query);
                ResultSet res=stmt.executeQuery();

                out.print ("<html><body>");
                out.print ("<h2>Corsi</h2>");
                out.print ("<h4>Corsi presenti: </h4>");

                while(res.next()){
                    out.print("<p>Codice: <a href='redirect.jsp?nome=" + res.getString("nome_corso")+ "'>" + res.getString("codice_corso") + "</a>"+
                    ", Nome:"+res.getString("nome_corso")+
                    ", descrizione: "+res.getString("descrizione")+", crediti"+res.getString("crediti")+"</p>");
                }

                out.print("<a href='index.jsp'><button>Torna alla home page</button></a>");
                out.print ("</body></html>");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
         try{
            response.setContentType("text/html");
            PrintWriter out=response.getWriter();

            if(request.getParameter("action").equals("create")){
                String nome_corso=request.getParameter("nome_corso");
                String descrizione=request.getParameter("descrizione");
                String crediti=request.getParameter("crediti");
                String query="INSERT INTO courses (nome_corso, descrizione, crediti) VALUES (?, ?, ?)";
                PreparedStatement stmt=conn.prepareStatement(query);
                stmt.setString(1, nome_corso);
                stmt.setString(2, descrizione);
                stmt.setString(3, crediti);
                stmt.executeUpdate();

                out.print("Corso inserito corretamente"+"<br>");
            }

            if(request.getParameter("action").equals("update")) {

                String query = "UPDATE courses SET descrizione=?, crediti=? WHERE nome_corso=?";
                PreparedStatement stmt = conn.prepareStatement(query);

                String nome = request.getParameter("nome");
                String descrizione = request.getParameter("descrizione");
                String crediti = request.getParameter("crediti");

                stmt.setString(1, descrizione);
                stmt.setString(2, crediti);
                stmt.setString(3, nome);

                stmt.executeUpdate();

                out.println("<h3>Corso di "+nome+" aggiornato con successo</h3><br>");
            }

            if(request.getParameter("action").equals("delete")){
                String nome_corso=request.getParameter("nome");
                String query="DELETE FROM courses WHERE nome_corso=?";
                PreparedStatement stmt=conn.prepareStatement(query);
                stmt.setString(1, nome_corso);
                stmt.executeUpdate();

                out.print("Corso eliminato corretamente"+"<br>");
            }

            out.print("<a href='index.jsp'><button>Torna alla home page</button></a>");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
