/* Server Socket con Storico

    Implementa un server, usando il linguaggio di programmazione di tua scelta (come Python, C o Java), che risponde sulla porta 8888. 
    Il server deve mantenere un registro delle richieste effettuate dai client. Il registro può essere implementato come una lista o un array.

    Il server deve gestire le seguenti richieste:

    - GET: Quando il server riceve la stringa "GET", deve rispondere inviando al client tutto l'elenco delle richieste memorizzate nel registro, separato 
           da virgole.

    - ADD <testo>: Quando il server riceve una richiesta in formato "ADD <testo>", deve aggiungere il testo al registro delle richieste e rispondere 
                   con "Richiesta aggiunta con successo."

    - COUNT: Quando il server riceve la stringa "COUNT", deve rispondere con il numero totale di richieste memorizzate nel registro.
    
    - CLEAR: Quando il server riceve la stringa "CLEAR", deve cancellare tutte le richieste dal registro e rispondere con "Registro svuotato con successo."
    
    Dopo aver elaborato la richiesta, il server deve chiudere la connessione con il client e tornare in attesa di ulteriori richieste.

    Implementa anche un client per testare il server. Il client dovrebbe consentire all'utente di inviare comandi al server e visualizzare le risposte.

    Assicurati che il server sia in grado di gestire più client contemporaneamente usando thread o processi, se necessario.

    Facoltativo per la prova in itinere, obbligatorio per l'esame completo: implementa una funzione di registrazione delle richieste su disco in modo 
    che il registro sia persistente tra riavvii del server. 
*/

import java.net.*;
import java.io.*;
import java.util.*;

public class Server{
    static final int port=8888;
    static List<String> registro=new ArrayList<>();

    static void get(PrintWriter out, List<String> registro){
        String tmp=String.join(",", registro);
        out.println(tmp);
    }

    static void add(PrintWriter out, List<String> registro, String s){
        registro.add(s);
        out.println("Richiesta aggiunta con successo");
    }

    static void clear(PrintWriter out, List<String> registro){
        registro.clear();
        out.println("Registro svuotato con successo");
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
                if(buffer.equalsIgnoreCase("GET")){
                    get(out, registro);
                }else if(buffer.startsWith("ADD")){
                    add(out, registro, buffer.substring(4, buffer.length()));
                }else if(buffer.equalsIgnoreCase("COUNT")){
                    out.println(registro.size());
                }else if(buffer.equalsIgnoreCase("CLEAR")){
                    clear(out, registro);
                }else{
                    out.println("Richiesta non valida");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}