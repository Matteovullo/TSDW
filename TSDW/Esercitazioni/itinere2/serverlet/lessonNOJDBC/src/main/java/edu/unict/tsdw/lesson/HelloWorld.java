package edu.unict.tsdw.lesson;

//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// @WebServlet("/hello")
public class HelloWorld extends HttpServlet {
    Connection connection; 
    final String connectionString = "jdbc:mysql://localhost:3306/students?user=root&password=root";

    public void init() {
        System.out.println("Servlet " + this.getServletName() + " has started");
        try {
             connection = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            // This print goes into catalina.out or standard output with Jetty
            System.out.println("Error while connecting to database");
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            return;
        }
        System.out.println("Connection Successfull");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.write("<html>");
        out.write("<head><title></title></head>");
        out.write("<body>");
        out.write("<h1>Hello Servlet World</h1>");
        out.write("</body></html>");

        try {
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery("select name from student");
            while (result.next()) {
                out.write("<p>" + result.getString("name") + "</p>");
            }
        } catch (SQLException e) {
            System.out.println("Error while querying the database");
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }
}