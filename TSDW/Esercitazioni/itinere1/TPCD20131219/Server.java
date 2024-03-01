import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static int port = 7777;
    static String[] V = new String[10];

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            V[i] = null;
        }

        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = server.accept();
                Thread clientThread = new Thread(new ClientHandler(socket));
                clientThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (
                PrintWriter out = new PrintWriter(socket.getOutputStream(), false);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
            ) {
                System.out.println("New client connected: " + socket.getInetAddress());

                String buffer;
                while ((buffer = in.readLine()) != null) {
                    if (buffer.equals("LIST")) {
                        StringBuilder tmp = new StringBuilder("V[] = ");
                        for (int i = 0; i < 10; i++) {
                            if (V[i] != null) {
                                tmp.append(V[i]).append("\n");
                            }
                        }
                        out.println(tmp.toString());
                        out.flush();
                    }else {
                        boolean inserito = false;
                        boolean spazioEsaurito = true;
                        for (int i = 0; i < 10; i++) {
                            if (V[i] != null && V[i].equals(buffer)) {
                                out.println("presente");
                                out.flush();
                                spazioEsaurito = false;
                                break;
                            }
                            if (V[i] == null) {
                                V[i] = buffer;
                                out.println("inserita");
                                out.flush();
                                inserito = true;
                                spazioEsaurito = false;
                                break;
                            }
                        }
                        
                        if (spazioEsaurito) {
                            int posizioneCasuale = (int)(Math.random() * 10); // Genera una posizione casuale
                            V[posizioneCasuale] = buffer; // Sovrascrive la posizione casuale
                            out.println("inserita (sovrascritto)");
                            out.flush();
                        } else if (!inserito) {
                            out.println("spazio esaurito"); // Notifica al client che il vettore è pieno
                            out.flush();
                        }
                    }
                    

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
