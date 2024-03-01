package com.example;

import java.io.*;
import java.sql.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/servlet")
public class UtentiServlet extends HttpServlet{
    
    Connection conn=null;

    public void init(){
        try{
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Database?user=root&password=root");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response){
        try{
            response.setContentType("text/html");
            PrintWriter out=response.getWriter();

            if(request.getParameter("action").equals("read")){
                String query="SELECT * FROM utenti";
                PreparedStatement stmt=conn.prepareStatement(query);
                ResultSet res=stmt.executeQuery();

                if(res.next()){
                    out.print("<p>Username: "+res.getString("username")+
                    ", password: "+res.getString("password"));
                }
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
                String username=request.getParameter("username");
                String password=request.getParameter("password");
                String query="INSERT INTO utenti (username, password) VALUES (?, ?)";
                PreparedStatement stmt=conn.prepareStatement(query);
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.executeUpdate();

                out.print("Utente aggiunto correttamente");
            }

            if(request.getParameter("action").equals("update")){
                String username=request.getParameter("username");
                String password=request.getParameter("password");
                String n_username=request.getParameter("n_username");
                String n_password=request.getParameter("n_password");
                String query="UPDATE utenti SET username=?, password=? WHERE username=? AND password=?";
                PreparedStatement stmt=conn.prepareStatement(query);
                stmt.setString(1, n_username);
                stmt.setString(2, n_password);
                stmt.setString(3, username);
                stmt.setString(4, password);
                stmt.executeUpdate();

                out.print("Utente modificato correttamente");
            }

            if(request.getParameter("action").equals("delete")){
                String username=request.getParameter("username");
                String password=request.getParameter("password");
                String query="DELETE FROM utenti WHERE username=? AND password=?";
                PreparedStatement stmt=conn.prepareStatement(query);
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.executeUpdate();

                out.print("Utente eliminato correttamente");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
