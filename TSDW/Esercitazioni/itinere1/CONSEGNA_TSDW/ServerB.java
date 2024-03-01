import java.io.*;
import java.net.*;

public class ServerB{
    static int port=3333;

    public static void main(String[] args){
        try(
            ServerSocket server=new ServerSocket(port);
            Socket socket=server.accept();
            PrintWriter out=new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ){
            String buffer=" ";
            boolean t=true;
            while((buffer=in.readLine())!=null){
                for(int i=0; i<buffer.length(); i++){
                    if(buffer.charAt(i) == 'V' || buffer.charAt(i) == 'F'){
                        t=true;
                    }else{
                        t=false;
                    }
                }
                    
                if(!t)
                    System.out.println("Messaggio ricevuto non valido: "+buffer);
                else
                    System.out.println("Messaggio ricevuto: "+buffer);

                out.println("Messaggio invito: "+buffer);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
