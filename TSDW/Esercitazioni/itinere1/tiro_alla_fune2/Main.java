import java.util.*;

public class Main {
    static volatile int posizione=0;
    static volatile int vittorie_tp0=0;
    static volatile int vittorie_tp1=0;
    static Random rand=new Random();
    static Object lock=new Object();

    static synchronized void vittoria0(){
        vittorie_tp0++;
    }
    
    static synchronized void vittoria1(){
        vittorie_tp1++;
    }

    static synchronized void changep(int p){
        posizione=p;
    }

    static synchronized void changen(int p){
        posizione-=p;
    }

    public static void main(String[] args){
        Thread tp0=new Thread(()->{
            int forza=0;
            int recupero=0;
            while(true){
                forza=rand.nextInt(4);
                recupero=rand.nextInt(6);

                try{
                    Thread.sleep(recupero*100);
                }catch(Exception e){
                    e.printStackTrace();
                }

                if(vittorie_tp0 >= 10 && vittorie_tp1 >=10){
                    if(vittorie_tp0>vittorie_tp1) System.out.println("Ha vinto tp0");
                    else System.out.println("Ha vinto tp1");

                    break;
                }

                if(posizione>=10){
                    vittoria0();
                    changep(0);
                    synchronized(lock){
                        lock.notify();
                    }
                }else{
                    changen(forza);
                    if(posizione<=-10){
                        vittoria0();
                        synchronized(lock){
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                System.out.println(vittorie_tp0+" "+vittorie_tp1);
            }
        });

        Thread tp1=new Thread(()->{
            int forza=0;
            int recupero=0;
            while(true){
                forza=rand.nextInt(4);
                recupero=rand.nextInt(6);

                try{
                    Thread.sleep(recupero*100);
                }catch(Exception e){
                    e.printStackTrace();
                }

                if(vittorie_tp0 >= 10 && vittorie_tp1 >=10){
                    if(vittorie_tp0>vittorie_tp1) System.out.println("Ha vinto tp0");
                    else System.out.println("Ha vinto tp1");

                    break;
                }

                if(posizione<=-10){
                    vittoria1();
                    changep(0);
                    synchronized(lock){
                        lock.notify();
                    }
                }else{
                    changen(forza);
                    if(posizione>=10){
                        vittoria1();
                        synchronized(lock){
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        
        tp0.start();
        tp1.start();

        try{
            tp0.join();
            tp1.join();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}