import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Esercizio{
    static int sample=50;
    static final ReentrantLock lock=new ReentrantLock();

    public static void main(String args[]){
        Thread t1=new Thread(new MyRunneble(1));
        Thread t2=new Thread(new MyRunneble(2));
        Thread t3=new Thread(new MyRunneble(3));

        t1.start();
        t2.start();
        t3.start();

        try{
            t1.join();
            t2.join();
            t3.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    static class MyRunneble implements Runnable{
        private Random rand=new Random();
        int tmp=0;
        int n=0;

        MyRunneble(int _n){
            n=_n;
        }

        public void run(){
            while(true){
                lock.lock();
                try{
                    tmp=rand.nextInt(90)-10;
                    System.out.println("Sono il thread: "+n+ ", sample valeva: "+sample+", adesso vale: "+tmp); 
                    sample=tmp;
                }finally{
                    lock.unlock();
                }

                if(tmp==sample){
                    System.out.println("tmp: "+tmp+" ,sample: "+sample);
                    System.out.println("Thread "+n+" terminato!");
                    break;
                }
            }
        }
    }
}