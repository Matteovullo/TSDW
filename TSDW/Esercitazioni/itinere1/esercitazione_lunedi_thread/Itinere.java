import java.util.*;

public class Itinere{
    static volatile int x=0;
    static Random rand=new Random();
    static Object lock=new Object();

    static synchronized void setX(int y){
        x=y;
    }

    public static void main(String[] args){
        x=rand.nextInt(10);
        Thread T1=new Thread(()->{
            int m=0;
            while(true){
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }

                m=rand.nextInt(11);

                if(x==-1){
                    break;
                }

                if(m==x){
                    System.out.println("RISPOSTA CORRETTA");
                    setX(-1);
                }else if(Math.abs(m-x) < 5){
                    System.out.println("risposta MOLTO sbagliata");
                    synchronized(lock){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }else{
                    System.out.println("risposta sbagliata");
                }
            }
        });

        Thread T2=new Thread(()->{
            while(true){
                try{
                    Thread.sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }

                synchronized(lock){
                    lock.notifyAll();
                }

                if(x==-1){
                    break;
                }
            }
        });

        T1.start();
        T2.start();

        try{
            T1.join();
            T2.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}