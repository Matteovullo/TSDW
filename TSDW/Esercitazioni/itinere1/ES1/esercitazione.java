import java.util.Random;

public class esercitazione{
    volatile static int turno=0;
    static Object lock=new Object();
    static Random rand=new Random();

    synchronized static void setRound(int k){
        turno+=k;
    }
    public static void main(String[] args){

        Thread t0=new Thread(()->{
            int posizone=0;
            int d=0;

            while(true){
                if(turno==-1) break;

                if(posizone>=100){
                    setRound(-1);
                    System.out.println("100 superato");
                    synchronized(lock){
                        lock.notify();
                    }
                }else{
                    try{
                        Thread.sleep(500);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    synchronized(lock){
                        lock.notify();
                    }
                }

                if(turno==0){
                    d=rand.nextInt(7);
                    posizone+=d;
                    System.out.println("Incremento t0, posizione = "+posizone);
                    setRound(1);
                    synchronized(lock){
                        try{
                            lock.wait();
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread t1=new Thread(()->{
            int posizone=0;
            int d=0;

            while(true){
                if(turno==-1) break;

                if(turno==1){
                    d=rand.nextInt(7);
                    posizone+=d;
                    System.out.println("Incremento t1, posizione = "+posizone);
                    setRound(-1);
                    synchronized(lock){
                        try{
                            lock.wait();
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }

                if(posizone>=100){
                    setRound(-1);
                    System.out.println("100 superato");
                    synchronized(lock){
                        lock.notify();
                    }
                }else{
                    try{
                        Thread.sleep(500);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    synchronized(lock){
                        lock.notify();
                    }
                }
            }
        });  
        
        t0.start();
        t1.start();

        try{
            t0.join();
            t1.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
