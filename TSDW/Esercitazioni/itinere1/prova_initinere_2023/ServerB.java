import java.io.*;
import java.net.*;

public class ServerB {
    static int port=3333;

    public static void main(String[] args){
        try(
            ServerSocket server=new ServerSocket(port);
            Socket socket=server.accept();
            PrintWriter out=new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in =new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ){
            System.out.println("Connessione stabiita");
            String buffer="";
            buffer=in.readLine();
            System.out.println("la stringa ricevuta è "+buffer);
            out.println("Hai invitato la stringa: "+buffer);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
      
}
