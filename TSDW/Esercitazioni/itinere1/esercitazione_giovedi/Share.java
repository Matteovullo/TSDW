import java.util.*;

public class Share {
    static Random rand=new Random();
    volatile int m=rand.nextInt(10)+1;
    static Object lock=new Object();

    public void n(){
        synchronized(lock){
            lock.notify();
        }
    }

    public void w(){
        synchronized(lock){
            try{
                lock.wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public synchronized void change(){
        int tmp=rand.nextInt(10)+1;
        m=tmp;
    }
}
