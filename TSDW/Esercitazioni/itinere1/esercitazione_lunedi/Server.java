import java.io.*;
import java.net.*;

public class Server{
    static final int port=5533;

    static String reverse(String buffer){
        String tmp="";
        for(int i=buffer.length()-1; i>=0; i--){
            tmp+=buffer.charAt(i);
        }
        return tmp;
    }

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
                    out.println("Stringa invertita:"+reverse(buffer));
                }
            }catch(Exception e){
                e.printStackTrace();
            }
    }
}