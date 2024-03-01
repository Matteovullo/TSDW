package com.example;

import java.io.*;
import java.sql.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

@WebServlet("/servlet")
public class FilmServlet extends HttpServlet{

    Connection conn;

    public void init(){
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myDB?user=root&password=root");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response){
        try{
            response.setContentType("text/html");
            PrintWriter out=response.getWriter();

            if (request.getParameter("action").equals("read_wish")) {
                String query = "SELECT * FROM wlist";
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet res = stmt.executeQuery();
    
                out.print("<h1>Lista dei desideri:</h1>");
    
                while (res.next()) {
                    out.print("<p>Titolo: " + res.getString("titolo") + ", Regista: " + res.getString("regista") + "</p>");
                }
            }

            out.print("<a href='index.jsp'><button>Torna alla home page</button></a>");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        try{
            response.setContentType("text/html");
            PrintWriter out=response.getWriter();
    
            out.print("<html><body>");

            String actionParam = request.getParameter("action");
    
            if(actionParam != null && request.getParameter("action").equals("read")){
                String titolo = request.getParameter("titolo");
                String regista = request.getParameter("regista");
    
                String query="SELECT * FROM flist WHERE titolo=? AND regista=?";
                PreparedStatement stmt=conn.prepareStatement(query);
                stmt.setString(1, titolo);
                stmt.setString(2, regista);
                ResultSet res=stmt.executeQuery();
    
                out.print("<h1>Film richiesto/i:</h1>");
    
                if(res.next()){
                    do {
                        out.print("<p>Titolo: "+res.getString("titolo")+", Regista: "+res.getString("regista")+"</p>");
                    } while(res.next());
                }else{
                    //request.setAttribute("titolo", titolo);
                    //request.setAttribute("regista", regista);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/wish");
                    dispatcher.forward(request, response);
                }                
            }else if(actionParam != null && request.getParameter("action").equals("delete")){
                String query="DELETE FROM wlist";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.executeUpdate();

                out.print("<h4>Film rimosso correttamente</h4>");
            }
            /*else if (actionParam != null && actionParam.equals("conferma")) {
                System.out.println("Pulsante 'si' premuto");
                String titolo = request.getParameter("titolo");
                String regista = request.getParameter("regista");
                
                // Esegui l'aggiunta del film alla lista dei desideri
                String query = "INSERT INTO wlist (titolo, regista) VALUES (?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, titolo);
                stmt.setString(2, regista);
                stmt.executeUpdate();
                conn.commit();
                
                out.print("<p>Film aggiunto alla lista dei desideri</p><br>");
            }else if (actionParam != null && actionParam.equals("annulla")) {
                System.out.println("Pulsante 'no' premuto");
                // Reindirizza l'utente a un'altra pagina o alla home page
                response.sendRedirect("index.jsp");
            }*/
            
            out.print("<a href='index.jsp'><button>Torna alla home page</button></a>");
            out.print("</body></html>");
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    

}
