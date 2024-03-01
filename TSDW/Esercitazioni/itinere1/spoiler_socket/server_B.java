import java.io.*;
import java.net.*;

public class server_B{
    static int port=3333;
    public static void main(String[] args){
        try{
            while(true){
               ServerSocket server=new ServerSocket(port);
               Socket socket=server.accept();
               PrintWriter out=new PrintWriter(socket.getOutputStream(), true);
               BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));

               String buffer="";
               buffer=in.readLine();
                System.out.println("Stringa ricevuta: "+buffer);
                out.println("Stringa invita: "+buffer);

               server.close();
               socket.close();
               out.close();
               in.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}