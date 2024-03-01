import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Prova{
    static Random rand=new Random();
    static ReentrantLock lock1=new ReentrantLock();
    static Object lock2=new Object();
    static int m=0;
    public static void main(String[] args){

        m=rand.nextInt(10)-1;

        Thread P1=new Thread(()->{
            while(true){
                int tmp=0;
                if(m>=1 && m<=5){
                    tmp=rand.nextInt(10)+1;
                    lock1.lock();
                    try{
                        m=tmp;
                        System.out.println(tmp);
                    }finally{
                        lock1.unlock();
                    }
                    synchronized(lock2){
                        lock2.notify();
                    }
                }else if(m>=6 && m<=10){
                    synchronized(lock2){
                        try{
                            lock2.wait();
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread P2=new Thread(()->{
            while(true){
                int tmp=0;
                if(m>=6 && m<=10){
                    tmp=rand.nextInt(10)+1;
                    lock1.lock();
                    try{
                        m=tmp;
                        System.out.println(tmp);
                    }finally{
                        lock1.unlock();
                    }
                    synchronized(lock2){
                        lock2.notify();
                    }
                }else if(m>=1 && m<=5){
                    synchronized(lock2){
                        try{
                            lock2.wait();
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        P1.start();
        P2.start();

        try{
            P1.join();
            P2.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}