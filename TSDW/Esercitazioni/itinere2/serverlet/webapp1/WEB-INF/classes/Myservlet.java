import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/MyServlet")
public class Myservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void init() {
        System.out.println("Servlet is initialized");
    }

    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
    String email = req.getParameter("email");
    String password = req.getParameter("password");

    PrintWriter out = res.getWriter();

    try {
        // Carica il driver JDBC
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Imposta i dettagli di connessione al database
        String url = "jdbc:mysql://localhost:3306/Database";
        String dbusername = "root";
        String dbPassword = "root";

        // Crea la connessione al database
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbPassword)) {
            // Esegue la query per ottenere l'username
            String query = "SELECT username FROM utenti WHERE email = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, email);
                statement.setString(2, password);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        // Restituisci l'username
                        String username = resultSet.getString("username");
                        out.println("Benvenuto " + username);
                    } else {
                        out.println("Login non riuscito. Verifica le tue credenziali");
                    }
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public void destroy() {
        System.out.println("Servlet is destroyed");
    }
}
