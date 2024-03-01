import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerC{

    static final int port=3333; 
    static int sommacifre(String s){
        return 0;
    }

    public static void main(String args[]){
        System.out.println("In ascolto...");

        try(
            ServerSocket server=new ServerSocket(port);
            Socket socket=server.accept();
            PrintWriter out=new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ){
            System.out.println("Connessione stabilita!");

            String buffer="";
            int n=0;
            while((buffer=in.readLine()) != null){
                int x=sommacifre(buffer);
                out.println(n+" "+x);
                n++;
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
