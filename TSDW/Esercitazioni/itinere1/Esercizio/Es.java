import java.io.*;
import java.net.*;

public class Es{
    static int port=7777;
    static int conto[]=new int[10];
    public static void main(String[] args){
        for(int i=0; i<conto.length; i++){
            conto[i]=0;
        }
        int conto_attuale=0;
        try(
            ServerSocket server=new ServerSocket(port);
            Socket socket=server.accept();
            PrintWriter out=new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ){
            System.out.println("Connessine stabilita");
            String buffer="";
            while((buffer=in.readLine())!=null){
                if(buffer.charAt(0)=='U'){
                    String tmp="";
                    tmp+=buffer.charAt(1);
                    conto_attuale=Integer.parseInt(tmp);
                    out.println("selezioanto il conto "+conto_attuale);
                }
                if(buffer.charAt(0)=='V'){
                    conto[conto_attuale]+=Integer.parseInt(buffer.substring(1, buffer.length()));
                    out.println("versati "+Integer.parseInt(buffer.substring(1, buffer.length()))+" euro");
                }if(buffer.charAt(0)=='P'){
                    conto[conto_attuale]-=Integer.parseInt(buffer.substring(1, buffer.length()));
                    out.println("prelavati "+Integer.parseInt(buffer.substring(1, buffer.length()))+" euro");
                }if(buffer.charAt(0)=='S'){
                    out.println("Saldo attuale del conto "+conto_attuale+" è "+conto[conto_attuale]);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}