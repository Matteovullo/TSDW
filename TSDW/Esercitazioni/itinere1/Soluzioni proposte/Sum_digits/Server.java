import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server 
{

    // Function to calculate the sum of digits in a string
    static int getSum(String buffer) 
    {
        int sumValue = 0;
        for (int i = 0; i < buffer.length(); i++) 
        {
            char c = buffer.charAt(i);

            if (c >= '0' && c <= '9')
                sumValue += Character.getNumericValue(c);
        }
        return sumValue;
    }

    public static void main(String[] args) 
    {
        if (args.length != 1) 
        {
            System.out.println("Usage: Server <port>");
            System.err.println("Error: Invalid arguments");
            System.exit(1);
        }

        int port = Integer.parseInt(args[0]);
        System.out.println("[+]Server listening on port " + port + "\n");

        // Try-with-resources automatically closes resources when the try block exits
        try (
            ServerSocket serverSocket = new ServerSocket(port); 
            Socket socket = serverSocket.accept(); // Accept incoming client connection
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ) 
        {
            String recvline = "";
            while ((recvline = in.readLine()) != null) 
            {
                System.out.println("Client: " + recvline);
                int sumValue = getSum(recvline);
                System.out.println("Outcome: " + sumValue + "\n");

                // Send the outcome back 
                out.println(sumValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("[+]Connection closed...");
    }
}
