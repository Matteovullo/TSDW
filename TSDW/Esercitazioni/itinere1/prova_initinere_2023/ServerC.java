import java.io.*;
import java.net.*;

public class ServerC {
    static int port=3333;

    public static void main(String[] args){
        try(
            ServerSocket server=new ServerSocket(port);
            Socket socket=server.accept();
            PrintWriter out=new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in =new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ){
            System.out.println("Connessione stabiita");
            String buffer=" ";
            while((buffer=in.readLine())!=null){
                if(buffer.charAt(0)=='a' || buffer.charAt(0)=='e' ||
                buffer.charAt(0)=='i' || buffer.charAt(0)=='o' ||
                buffer.charAt(0)=='u' || buffer.charAt(0)=='A' ||
                buffer.charAt(0)=='E' || buffer.charAt(0)=='I' ||
                buffer.charAt(0)=='O' || buffer.charAt(0)=='U'){
                    out.println("L'ultima stringa per vocale è: "+buffer);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
      
}