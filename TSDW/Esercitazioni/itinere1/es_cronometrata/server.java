import java.io.*;
import java.net.*;
//import java.util.*;

public class server{
    static int port=7777;
    static String[] V=new String[10];

    static String LIST(String[] v){
        String str="";
        for(int i=0; i<v.length; i++){
            str+=v[i]+"\n";
        }
        return str;
    }

    public static void main(String[] args){
        try(
            ServerSocket server=new ServerSocket(port);
            Socket socket=server.accept();
            PrintWriter out=new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ){
            System.out.println("Conesssione stabilita");

                V[0]=" ";
                V[1]=" ";
                V[2]=" ";
                V[3]=" ";
                V[4]=" ";
                V[5]=" ";
                V[6]=" ";
                V[7]=" ";
                V[8]=" ";
                V[9]=" ";

            String buffer=" ";
            boolean isPresent=false;
            int index=0;
            //Random rand=new Random();
            while((buffer=in.readLine()) != null){
                if(buffer.equalsIgnoreCase("LIST")){
                    out.println(LIST(V));
                }else{
                    for(int i=0; i<V.length; i++){
                        if(V[i].equals(buffer)){
                            out.println("presente");
                            isPresent=true;
                            break;
                        }
                    }
                    if (!isPresent)
                    {
                        System.out.println("Inserita\n");
                        V[index] = new String(buffer);
                        out.println("Inserita");
                        index = (index + 1) % V.length;
                        isPresent = false;
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}