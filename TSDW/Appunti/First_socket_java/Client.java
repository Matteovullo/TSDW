import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client 
{
    public static void main(String[] args) 
    {

        if (args.length != 2) 
        {
            System.out.println("Usage: Client <ip> <port>");
            System.err.println("Error arguments");
            System.exit(1);
        }

        String serverAddress = args[0];
        int port = Integer.parseInt(args[1]);

        // try-with-resources : resources are automatically closed when the try block ends
        try (
             Scanner keyboard = new Scanner(System.in);
             Socket socket = new Socket(serverAddress, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // auto-flush
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ) 
        {
            System.out.println("[+]Connected to server " + serverAddress + ", port: " + port + "\n");

            while (true) 
            {
                System.out.print("Enter a message (or 'exit' to quit): ");
                String buffer = keyboard.nextLine(); 
                
                if ("exit".equalsIgnoreCase(buffer)) 
                    break;

                // send 
                out.println(buffer);

                // receive 
                buffer = in.readLine();
                System.out.println("Server: " + buffer + "\n");
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }

        System.out.println("\n[+]Connection closed...");
    }
}
