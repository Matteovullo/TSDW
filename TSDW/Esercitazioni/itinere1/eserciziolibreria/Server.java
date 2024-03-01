/*
	Realizzare un server che tiene traccia della disponibilità di 10 libri, che possono essere 
    disponibili o in prestito;
	riceve delle richieste da parte dei client del tipo "titolo del libro"
	e risponde "Disponibile", "In prestito" o "Inesistente" a seconda del titolo richiesto.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server 
{
    public record Book (String nome, String stato){}

    static Book[] liberia = {   new Book("Harry Potter", "Disponibile"),
                                new Book("Architettura degli elabotori", "In prestito"),
                                new Book("Intorduzione al data mining", "In prestito"),
                                new Book("Manuale c", "Disponibile"),
                                new Book("Manuale java", "In prestito"),
                                new Book("Manuale python", "In prestito"),
                                new Book("Ingengeria del software", "In prestito"),
                                new Book("Data base", "In prestito"),
                                new Book("Geoeetria", "In prestito"),
                                new Book("Il signore degli anelli", "In prestito"),
                                new Book("minchia di Alfio", "Ciclopica")
                            };

    static String search(String book)
    {
        for(Book b : liberia)
        {
            if(b.nome().equals(book))
                return b.stato();
        }
        return "Inesistente";
    }

    public static void main(String args[])
    {
        if(args.length != 1)
        {
            System.out.println("Inserire una porta!");
        }

        int port = Integer.parseInt(args[0]);
        System.out.println("In ascolto sulla porta " + port);

        try(ServerSocket server=new ServerSocket(port);
            Socket socket=server.accept();
            PrintWriter out=new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream())))
        {
            String buffer = null;
            while((buffer=in.readLine()) != null)
            {
                out.println("Stato: "+search(buffer));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}