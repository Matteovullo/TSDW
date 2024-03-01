package com.example;

import java.io.*;
import java.sql.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/farmacia")
public class MedicinaliServlet extends HttpServlet{
    
    Connection conn=null;

    public void init(){
        try{
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Database?user=root&password=root");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        try{
            response.setContentType("text/html");
            PrintWriter out=response.getWriter();

            if(request.getParameter("action").equals("create")){
                String nome=request.getParameter("nome");
                String principio=request.getParameter("principio");
                String forma=request.getParameter("forma");
                String dosaggio=request.getParameter("dosaggio");
                String scadenza=request.getParameter("scadenza");

                String query="INSERT INTO farmacia (nome, principio, forma, dosaggio, scadenza) VALUES (?,?,?,?,?)";
                PreparedStatement stmt=conn.prepareStatement(query);
                stmt.setString(1, nome);
                stmt.setString(2, principio);
                stmt.setString(3, forma);
                stmt.setString(4, dosaggio);
                stmt.setString(5, scadenza);
                stmt.executeUpdate();
                
                out.print("Farmaco inserito correttamente");
            }

            if(request.getParameter("action").equals("delete")){
                String nome=request.getParameter("nome");

                String query="DELETE FROM farmacia WHERE nome=?";
                PreparedStatement stmt=conn.prepareStatement(query);
                stmt.setString(1, nome);
                stmt.executeUpdate();
                
                out.print("Farmaco eliminato correttamente");
            }

            if(request.getParameter("action").equals("update")){
                String nome=request.getParameter("nome");
                String scadenza=request.getParameter("scadenza");

                String query="UPDATE farmacia SET scadenza=? WHERE nome=?";
                PreparedStatement stmt=conn.prepareStatement(query);
                stmt.setString(1, scadenza);
                stmt.setString(2, nome);
                stmt.executeUpdate();
                
                out.print("Farmaco aggiornato correttamente");
            }

            if(request.getParameter("action").equals("read2")){
                String nome=request.getParameter("tutti");

                String query="SELECT * FROM farmacia WHERE nome=?";
                PreparedStatement stmt=conn.prepareStatement(query);
                stmt.setString(1, nome);
                ResultSet res=stmt.executeQuery();
                
                if(res.next()){
                    out.print("<p>ID: "+res.getString("id")+", Nome: "+res.getString("nome")+
                    ", principio: "+res.getString("principio")+", forma: "+res.getString("forma")+
                    ", dosaggio: "+res.getString("dosaggio")+", scadenza: "+res.getString("scadenza")+"<p>");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response){
        try{
            response.setContentType("text/html");
            PrintWriter out=response.getWriter();

            if(request.getParameter("action").equals("read")){
                String query="SELECT * FROM farmacia";
                PreparedStatement stmt=conn.prepareStatement(query);
                ResultSet res=stmt.executeQuery();
                
                while(res.next()){
                    out.print("<p>ID: "+res.getString("id")+", Nome: "+res.getString("nome")+
                    ", principio: "+res.getString("principio")+", forma: "+res.getString("forma")+
                    ", dosaggio: "+res.getString("dosaggio")+", scadenza: "+res.getString("scadenza")+"<p>");
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
