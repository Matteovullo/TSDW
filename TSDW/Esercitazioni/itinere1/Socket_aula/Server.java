import java.net.*;
import java.io.*;

public class Server{
    static int port=3333;

    static boolean check(String buffer){
        boolean c=true;
        for(int i=0; i<buffer.length()-2; i++){
            if(buffer.charAt(i) != 'v' || buffer.charAt(i) != 'V'){
                c=false;
                break;
            }
        }
        return c;
    }
    public static void main(String[] args){
        try(
            ServerSocket server=new ServerSocket(port);
            Socket socket=server.accept();
            PrintWriter out=new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ){
            String buffer="";
            while((buffer=in.readLine())!=null){
                out.println(check(buffer));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}