import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerD{

    static final int port=3333; 
    static int sommacifre(String s) {
        int somma = 0;

        for (int i = 0; i < s.length(); i++) {
            char carattere = s.charAt(i);
            if (Character.isDigit(carattere)) {
                somma += Character.getNumericValue(carattere);
            }
        }

        return somma;
    }

    public static void main(String args[]){
        System.out.println("In ascolto...");

        try(
            ServerSocket server=new ServerSocket(port);
            Socket socket=server.accept();
            PrintWriter out=new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ){
            System.out.println("Connessione stabilita!");

            String buffer="";
            while((buffer=in.readLine()) != null){
                //System.out.println(buffer);
                int sum=sommacifre(buffer);
                out.println("La somma è "+sum);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        System.out.println("Conessione in chiusura...");
    }
}