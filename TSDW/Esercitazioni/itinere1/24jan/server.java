import java.io.*;
import java.net.*;

public class server{
    static int port=7777;
    static String[] animali=new String[3];
    static int[] voti=new int[3];
    static int[] n=new int[3];

    public static void main(String[] args){
        animali[0]="cane";
        animali[1]="gatto";
        animali[2]="usignolo";

        voti[0]=0;
        voti[1]=0;
        voti[2]=0;

        n[0]=0;
        n[1]=0;
        n[2]=0;

        try(
            ServerSocket server=new ServerSocket(port);
            Socket socket=server.accept();
            PrintWriter out=new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ){
            System.out.println("Connessione riuscita");
            String buffer="";
            while((buffer=in.readLine())!=null){
                String t="";
                int voti_totali=0;
                t+=buffer.charAt(1);

                if(buffer.equalsIgnoreCase("G")){
                    for(int i=0; i<3; i++){
                        out.println(animali[i]);
                    }
                }else if(buffer.charAt(0)=='V'){
                    //String tmp1="";
                    //String tmp2="";
                    //tmp1+=buffer.charAt(0);
                    //tmp2+=buffer.charAt(1);
                    //int v=Integer.parseInt(tmp1);
                    int k=Integer.parseInt(t);

                    //System.out.println(v+", "+k);

                    if(k<0 && k>2){
                        out.println("ERROR");
                    }else{
                        voti[k]++;
                        n[k]++;
                        voti_totali++;
                        String tmp = "";
                        float y =0;
                        for (int i = 0; i < 3; i++) {
                            if(voti[i]>0) y= (float) voti[i] / n[i] * 100;
                            else y=0;
                            tmp += animali[i] + ": " + y + "%\n";
                        }
                        out.println(tmp);
                    }
                }else{
                    out.println("ERROR");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}