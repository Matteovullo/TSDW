package com.example;

import java.io.*;
import java.sql.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

@WebServlet("/servlet")
public class FumettiServlet extends HttpServlet{
    
    Connection conn=null;

    public void init(){
        try{
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/fumetteria?user=root&password=root");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        try{
            response.setContentType("text/html");
            PrintWriter out=response.getWriter();

            String[] columnsToCheck = {"nome_autore", "cognome_autore"};

            for (String columnName : columnsToCheck) {
                String query = "SELECT COUNT(*) AS count_column " +
                               "FROM information_schema.columns " +
                               "WHERE table_schema = 'fumetteria' " +
                               "AND table_name = 'fumetti' " +
                               "AND column_name = ?";

                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, columnName);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    int count = rs.getInt("count_column");

                    if (count == 0) {
                        String alterQuery = "ALTER TABLE fumetti ADD COLUMN " + columnName + " VARCHAR(45) NULL AFTER anni";
                        conn.createStatement().executeUpdate(alterQuery);

                        out.println("Colonna '" + columnName + "' aggiunta con successo.<br>");
                    } else {
                        out.println("La colonna '" + columnName + "' esiste già nella tabella.<br>");
                    }
                }

                rs.close();
                pstmt.close();
            }

            String query="SELECT * FROM fumetti";
            PreparedStatement stmt=conn.prepareStatement(query);
            ResultSet res=stmt.executeQuery();

            while(res.next()){
                int id=Integer.parseInt(res.getString("id"));
                String nome_cognome=res.getString("autore");
                String[] parts=nome_cognome.split("-");
                
                String q="UPDATE fumetti SET nome_autore=?, cognome_autore=? WHERE id=?";
                PreparedStatement s=conn.prepareStatement(q);
                s.setString(1, parts[0]);
                s.setString(2, parts[1]);
                s.setInt(3, id);

                s.executeUpdate();
                out.print("<p>Aggiornato correttamente</p>");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
