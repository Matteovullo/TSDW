package com.example;

import java.sql.*;
import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/servlet")
public class RistoranteServlet extends HttpServlet{
    
    Connection conn=null;

    public void init(){
        try{
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ristorante?user=root&password=root");
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
                String username=request.getParameter("username");
                String password=request.getParameter("password");

                String query="INSERT INTO cameriere (nome, cognome, username, password) VALUES (?, ?, ?, ?)";
                PreparedStatement stmt=conn.prepareStatement(query);
                stmt.setString(1, nome);
                stmt.setString(2, cognome);
                stmt.setString(3, username);
                stmt.setString(4, password);
                stmt.executeUpdate();

                out.print("<p>Cameriere inserito corretamente");
            }

            if(request.getParameter("action").equals("update_l")){
                String id=request.getParameter("id");
                String id_cameriere=null;

                String query="UPDATE tavolo SET id_cameriere=?, stato=? WHERE id=?";
                PreparedStatement stmt=conn.prepareStatement(query);
                stmt.setString(1, id_cameriere);
                stmt.setString(2, "libero");
                stmt.setString(3, id);
                stmt.executeUpdate();

                out.print("<p>Stato cambiato corretamente");
            }

            if(request.getParameter("action").equals("update_o")){
                String id=request.getParameter("id");
                String id_cameriere=request.getParameter("id_cameriere");

                String query="UPDATE tavolo SET id_cameriere=?, stato=? WHERE id=?";
                PreparedStatement stmt=conn.prepareStatement(query);
                stmt.setString(1, id_cameriere);
                stmt.setString(2, "occupato");
                stmt.setString(3, id);
                stmt.executeUpdate();

                out.print("<p>Stato cambiato corretamente");
            }

            if(request.getParameter("action").equals("delete")){
                String id=request.getParameter("id");
                String query="DELETE FROM cameriere WHERE id=?";
                PreparedStatement stmt=conn.prepareStatement(query);
                stmt.setString(1, id);
                stmt.executeUpdate();

                out.print("<p>Cameriere eliminato corretamente");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response){
        try{
            response.setContentType("text/html");
            PrintWriter out=response.getWriter();
            if(request.getParameter("action").equals("read_t")){
                String query="SELECT * FROM tavolo";
                PreparedStatement stmt=conn.prepareStatement(query);
                ResultSet res=stmt.executeQuery();

                while(res.next()){
                    out.print("<p>id: "+res.getString("id")+", id_camerire: "+res.getString("id_cameriere")+
                    ", num_posti: "+res.getString("num_posti")+", stato: "+res.getString("stato")+"<p>");

                    if(res.getString("stato").equals("occupato")){
                        out.print("<form action='/servlet' method='post'>");
                        out.print("<button type=submit>libera</button>");
                        out.print("<input type='hidden' value='update_l' name='action'>");
                        out.print("<input type='hidden' value='"+res.getString("id")+"' "+"name='id'>");
                        out.print("<input type='hidden' value='"+res.getString("id_cameriere")+"' "+"name='id_cameriere'>");
                        out.print("</form>");
                    }else if(res.getString("stato").equals("libero")){
                        out.print("<form action='/servlet' method='post'>");
                        out.print("<input type='hidden' value='update_o' name='action'>");
                        out.print("<input type='hidden' value='"+res.getString("id")+"' "+"name='id'>");
                        out.print("<label for='id_camariere'>inserire cameriere: </label>");
                        out.print("<input type='text' name='id_cameriere' id='id_cameriere'><br>");
                        out.print("<button type='submit'>occupa</button>");
                        out.print("</form>");
                    }
                }
            }

            if(request.getParameter("action").equals("read_c")){
                String query="SELECT * FROM cameriere";
                PreparedStatement stmt=conn.prepareStatement(query);
                ResultSet res=stmt.executeQuery();

                while(res.next()){
                    out.print("<p>id: "+res.getString("id")+", nome: "+res.getString("nome")+
                    ", cognome: "+res.getString("cognome")+", username: "+res.getString("username")+
                    ", password: "+res.getString("password")+"<p>");

                    out.print("<form action='/servlet' method='post'>");
                    out.print("<button type=submit>elimina</button>");
                    out.print("<input type='hidden' value='delete' name='action'>");
                    out.print("<input type='hidden' value='"+res.getString("id")+"' "+"name='id'>");
                    out.print("</form>");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
