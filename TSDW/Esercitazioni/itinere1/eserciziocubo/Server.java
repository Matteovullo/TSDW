import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{

    static final int port=3333; 
    static int cubo(int n){
        return (int)Math.pow(n, 3);
    }

    public static void main(String args[]){
        System.out.println("In ascolto...");

        try(
            ServerSocket server=new ServerSocket(port);
            Socket socket=server.accept();
            PrintWriter out=new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ){
            System.out.println("Conessione riusciuta...");

            String str=null;
            while((str=in.readLine()) != null){
                System.out.println("Client ha mandato il numero: "+str);
                int c=cubo(Integer.parseInt(str));
                out.println("Cube of "+str+" is "+c);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}