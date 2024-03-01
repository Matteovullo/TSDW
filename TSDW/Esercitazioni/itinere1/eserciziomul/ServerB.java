import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerB{
    static final int port=7777;
    public static void main(String args[]){
        System.out.println("Server in ascolto sulla porta "+port);

        try(
            ServerSocket server=new ServerSocket(port);
            Socket socket=server.accept();
            PrintWriter out=new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ){
            System.out.println("Connessione riuscita");
            String buffer=null;
            while((buffer=in.readLine()) != null){
                System.out.println("La stringa inviata è "+buffer);
                out.println("La stringa che hai inviato è "+buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Conessione in chiusura");
    }
}
