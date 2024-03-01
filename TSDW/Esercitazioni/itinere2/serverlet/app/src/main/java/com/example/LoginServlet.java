package com.example;

import java.io.*;
import jakarta.servlet.http.*;

public class LoginServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        out.write("<html>");
        out.write("<head><title></title></head>");
        out.write("<body>");
        out.write("<h1>Hello Servlet World</h1>");
        out.write("</body></html>");
    }
}
