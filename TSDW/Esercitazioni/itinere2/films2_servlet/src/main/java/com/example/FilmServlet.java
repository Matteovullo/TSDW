package com.example;

import java.sql.*;
import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/films")
public class FilmServlet extends HttpServlet{
    
    Connection conn=null;

    public void init(){
        try{
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Cinema?user=root&password=root");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        try{
            response.setContentType("text/html");
            PrintWriter out=response.getWriter();

            if(request.getParameter("action").equals("create")){
                String titolo=request.getParameter("titolo");
                String anno=request.getParameter("anno");
                String paese=request.getParameter("paese");
                String regista=request.getParameter("regista");

                String query="INSERT INTO films (titolo, anno, paese, regista) VALUES (?, ?, ?, ?)";
                PreparedStatement stmt=conn.prepareStatement(query);
                stmt.setString(1, titolo);
                stmt.setString(2, anno);
                stmt.setString(3, paese);
                stmt.setString(4, regista);

                stmt.executeUpdate();

                out.print("film inserito correttamente");
            }

            if(request.getParameter("action").equals("update")){
                String id=request.getParameter("id");
                String titolo=request.getParameter("titolo");
                String anno=request.getParameter("anno");
                String paese=request.getParameter("paese");
                String regista=request.getParameter("regista");

                String query="UPDATE films SET titolo=?, anno=?, paese=?, regista=? WHERE id=?";
                PreparedStatement stmt=conn.prepareStatement(query);
                stmt.setString(1, titolo);
                stmt.setString(2, anno);
                stmt.setString(3, paese);
                stmt.setString(4, regista);
                stmt.setString(5, id);

                stmt.executeUpdate();

                out.print("film aggiornato correttamente");
            }

            if(request.getParameter("action").equals("delete")){
                String titolo=request.getParameter("titolo");

                String query="DELETE FROM films WHERE titolo=?";
                PreparedStatement stmt=conn.prepareStatement(query);
                stmt.setString(1, titolo);

                stmt.executeUpdate();

                out.print("film eliminato correttamente");
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
                String query="SELECT * FROM films";
                PreparedStatement stmt=conn.prepareStatement(query);
                ResultSet res=stmt.executeQuery();

                while(res.next()){
                    out.print("<p>id: "+res.getString("id")+", titolo: "+res.getString("titolo")+
                    ", anno: "+res.getString("anno")+", paese: "+res.getString("paese")+
                    ", registra: "+res.getString("regista")+"</p>");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
