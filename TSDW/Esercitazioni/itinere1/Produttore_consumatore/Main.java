import java.util.*;

public class Main{
    static final int N=20;
    static volatile int buffer[]=new int[N];
    static volatile int item=0;
    static Object lock=new Object();

    static void print(){
        for(int i : buffer){
            System.out.print(i+" ");
        }
        System.out.println(" ");
    }

    static synchronized void removeItem(){
        if(item>0){
            buffer[--item]=0;
        }
        System.out.println("Item rimosso!");
        print();
    }

    static synchronized void addItem(int i){
        if(item<N-1){
            buffer[item++]=i;
        }
        System.out.println("Item aggiunto!");
        print();
    }

    static Thread producer=new Thread(()->{
        while(true){
            int tmp=new Random().nextInt(100)+1;
            addItem(tmp);
            try{
                Thread.sleep(3000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            if(item==N-1){
                synchronized(lock){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            synchronized(lock){
                lock.notify();
            }
        }
    });

    static Thread consumer=new Thread(()->{
        while(true){
            removeItem();
            try{
                Thread.sleep(3000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            if(item==0){
                synchronized(lock){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            synchronized(lock){
                lock.notify();
            }
        }
    });

    public static void main(String[] args) throws Exception{
        for(int i=0; i<N; i++){
            buffer[i]=0;
        }

        consumer.start();
        producer.start();

        consumer.join();
        producer.join();
    }
}