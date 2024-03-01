import java.io.InterruptedIOException;
import java.util.*;

public class Th extends Thread{
    Share share=new Share();
    Random rand=new Random();
    String id;

    public Th(String str){
        id=str;
    }

    public void run(){
        while(true){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int before=Share.sample;
            share.change(rand.nextInt(90)+11);
            if(before==share.sample){
                System.out.println("Thread "+id+" termina l'esecuzione"+ "before= "+before+", simple:"+share.sample);
                break;
            }
            System.out.println("Sono il thread "+id+":"+"sample valeva: "+before+", adesso vale: "+share.sample);
        }
    }
}
