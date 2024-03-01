import java.io.*;
import java.net.*;

public class server {
    static int port = 3233;
    static String[] db = new String[20];

    static void insert(String indirizzo) {
        for (int i = 0; i < 20; i++) {
            if (db[i].equalsIgnoreCase("")) {
                db[i] = indirizzo;
                break; // Exit the loop after inserting the address
            }
        }
    }

    static boolean check(String indirizzo) {
        int j = 0;
        for (int i = 0; i < 20; i++) {
            if (db[i].equalsIgnoreCase(indirizzo)) {
                j++;
                if (j > 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) db[i] = "";

        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("Server is running on port " + port);

            while (true) {
                Socket clientSocket = server.accept();
                System.out.println("Connection established with client: " + clientSocket.getInetAddress());

                try (
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ) {
                    String buffer;
                    while ((buffer = in.readLine()) != null) {
                        String clientIpAddress = clientSocket.getInetAddress().getHostAddress();
                        insert(clientIpAddress);
                        if (check(clientIpAddress)) {
                            out.println("banned");
                        } else if (buffer.equalsIgnoreCase("TIME")) {
                            out.println(System.currentTimeMillis());
                        } else {
                            out.println("N/A");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
