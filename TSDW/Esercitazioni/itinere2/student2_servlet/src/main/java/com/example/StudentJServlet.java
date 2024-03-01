package com.example;

import java.io.*;
import java.sql.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/servlet")
public class StudentJServlet extends HttpServlet{
    
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
                String cognome=request.getParameter("cognome");
                String corso_di_laurea=request.getParameter("corso_di_laurea");

                String query="INSERT INTO students (nome, cognome, corso_di_laurea) VALUES (?,?,?)";
                PreparedStatement stmt=conn.prepareStatement(query);
                stmt.setString(1, nome);
                stmt.setString(2, cognome);
                stmt.setString(3, corso_di_laurea);
                stmt.executeUpdate();

                out.print("Studente inserito correttmente");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response){
        try{
            response.setContentType("text/html");
            PrintWriter out=response.getWriter();

            String codice=request.getParameter("corso_di_laurea");
            String query="SELECT * FROM courses WHERE codice_corso=?";
            PreparedStatement stmt=conn.prepareStatement(query);
            stmt.setString(1, codice);
            ResultSet res=stmt.executeQuery();

            while(res.next()){
                out.print("<p>Nome corso: "+res.getString("nome_corso")+", descrizione: "+res.getString("descrizione")+
                ", crediti: "+res.getString("crediti")+"</p>");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
