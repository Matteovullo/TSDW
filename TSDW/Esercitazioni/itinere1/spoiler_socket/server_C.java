import java.io.*;
import java.net.*;
import java.util.*;

public class server_C{
    static int port=3333;
    static List<String> ls=new ArrayList<>();

    static String massimo(){
        return ls.stream().max(Comparator.comparing(x->x.length())).orElse(null);
    }
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
                ls.add(buffer);
                out.println("Stringa pu lunga: "+massimo());

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
