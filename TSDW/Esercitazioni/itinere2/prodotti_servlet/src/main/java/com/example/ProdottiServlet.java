package com.example;

import java.io.*;
import java.sql.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/servlet")
public class ProdottiServlet extends HttpServlet{
    
    Connection conn=null;

    public void init(){
        try{
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/magazzino?user=root&password=root");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response){
        try{
            response.setContentType("text/html");
            PrintWriter out=response.getWriter();

            if(request.getParameter("action").equals("read")){
                String query="SELECT * FROM prodotti";
                PreparedStatement stmt=conn.prepareStatement(query);
                ResultSet res=stmt.executeQuery();

                while(res.next()){
                    if(Integer.parseInt(res.getString("giacenza")) > 0){
                        out.print("<p>ID: "+res.getString("id")+", nome: "+res.getString("nome_prodotto")+
                        ", giacenza: "+res.getString("giacenza")+", prezzo: "+res.getString("prezzo"));
                        out.print("<form action='/servlet' method='post'>");
                        out.print("<input type='hidden' value='update' name='action'>");
                        out.print("<label for='giacenza'>Giacenza: </label>");
                        out.print("<input type='text' id='giacenza' name='giacenza' required>");
                        out.print("<input type='hidden' value='"+res.getString("nome_prodotto")+"' "+"name='nome'>");
                        out.print("<button type='submit'>compra</button>");
                        out.print("</form>");

                        out.print("<form action='/servlet' method='post'>");
                        out.print("<input type='hidden' value='delete' name='action'>");
                        out.print("<input type='hidden' value='"+res.getString("nome_prodotto")+"' "+"name='nome'>");
                        out.print("<button type='submit'>elimina</button>");
                        out.print("</form>");
                    }
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
                String nome=request.getParameter("nome");
                String giacenza=request.getParameter("giacenza");
                String prezzo=request.getParameter("prezzo");

                String query="INSERT INTO prodotti (nome_prodotto, giacenza, prezzo) VALUES (?, ?, ?)";
                PreparedStatement stmt=conn.prepareStatement(query);
                stmt.setString(1, nome);
                stmt.setString(2, giacenza);
                stmt.setString(3, prezzo);
                stmt.executeUpdate();

                out.print("Prodotto inserito correttamente");
            }

            if(request.getParameter("action").equals("update")){
                String nome=request.getParameter("nome");
                String giacenza=request.getParameter("giacenza");

                String query="UPDATE prodotti SET giacenza=giacenza-? WHERE nome_prodotto=?";
                PreparedStatement stmt=conn.prepareStatement(query);
                stmt.setString(1, giacenza);
                stmt.setString(2, nome);
                stmt.executeUpdate();

                out.print("Prodotto acquistato correttamente");
            }

            if(request.getParameter("action").equals("delete")){
                String nome=request.getParameter("nome");

                String query="DELETE FROM prodotti WHERE nome_prodotto=?";
                PreparedStatement stmt=conn.prepareStatement(query);
                stmt.setString(1, nome);
                stmt.executeUpdate();

                out.print("Prodotto eliminato correttamente");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
