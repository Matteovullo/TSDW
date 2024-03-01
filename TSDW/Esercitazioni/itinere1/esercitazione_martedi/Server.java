import java.io.*;
import java.net.*;

public class Server{
    static final int port=7777;

    public static void main(String[] args){
        try(
            ServerSocket server=new ServerSocket(port);
            Socket socket=server.accept();
            PrintWriter out=new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ){
            System.out.println("Connessione riuscita");
            String buffer="";
            while((buffer=in.readLine())!=null){
                out.println(buffer.length());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}