import java.io.*;
import java.net.*;

public class server{
    static int port=3333;
    static int cubo(int n){
        return n*n*n;
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
                int c=cubo(Integer.parseInt(buffer));
                out.println("Il cubo di "+buffer+" è "+c);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}