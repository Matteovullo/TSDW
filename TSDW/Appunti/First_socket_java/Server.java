import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server 
{
    static String handleRequest(String buffer)
    {
        return "Your message has " + buffer.length() + " characters";
    }

    public static void main(String[] args) 
    {        
        if (args.length != 1) 
        {
            System.out.println("Usage: Server <port>");
            System.err.println("Error arguments");
            System.exit(1);
        }

        int port = Integer.parseInt(args[0]);
        System.out.println("[+]Server listening on port " + port + "\n");

        try (
             ServerSocket serverSocket = new ServerSocket(port);  // bind
             Socket socket = serverSocket.accept();  // accept
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ) 
        {
            // receive
            String buffer;
            while ((buffer = in.readLine()) != null) 
            {
                // send
                System.out.println("Client: " + buffer);
                System.out.println("--> " + buffer.length() + " characters");
                out.println(handleRequest(buffer));
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }

        System.out.println("\n[+]Connection closed...");
    }
}
