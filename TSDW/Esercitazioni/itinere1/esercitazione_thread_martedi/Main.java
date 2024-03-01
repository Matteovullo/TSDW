import java.util.*;

public class Main{
    static volatile int x=0;
    static synchronized void incremento(){
        x++;
    }
    public static void main(String[] args){
        Thread tA=new Thread(new Myrunnable("A"));
        Thread tB=new Thread(new Myrunnable("B"));

        tA.start();
        tB.start();

        try{
            tA.join();
            tB.join();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    static public class Myrunnable implements Runnable{
        public Random rand=new Random();
        String nome="";
        int hit=0;

        Myrunnable(String n){
            nome=n;
        }

        public void run(){
            while(true){
                int tmp=rand.nextInt(11);
                try{
                    Thread.sleep(tmp*100);
                }catch(Exception e){
                    e.printStackTrace();
                }

                if(x>500){
                    System.out.println("Thread"+nome+", hit: "+hit);
                    break;
                }else{
                    incremento();
                    hit++;
                    System.out.println("Thread"+nome+", hit: "+hit+", x: "+x);
                }
            }
        }
    }
}