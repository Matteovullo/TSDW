import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerD{
    static final int port=7777;

    static int MUL(String str) {
        int result = 1;
        for (int i = 0; i < str.length(); i++) {
            char tmp = str.charAt(i);
            if (Character.isDigit(tmp)) {
                int digit = Integer.parseInt(String.valueOf(tmp));
                result *= digit;
            }
        }
        return result;
    }

    public static void main(String args[]){
        System.out.println("Server in ascolto sulla porta "+port);

        try(
            ServerSocket server=new ServerSocket(port);
            Socket socket=server.accept();
            PrintWriter out=new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ){
            System.out.println("Connessione riuscita");
            String buffer=null;
            while((buffer=in.readLine()) != null){
                System.out.println("La stringa inviata è "+buffer);
                int mul=MUL(buffer);
                out.println("MUL = "+mul);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Conessione in chiusura");
    }
}
