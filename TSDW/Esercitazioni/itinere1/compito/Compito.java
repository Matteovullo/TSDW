import java.util.Random;

public class Compito{
    volatile static int x=0;
    static Object lock=new Object();
    static Random rand=new Random();
    public static void main(String args[]) throws InterruptedException{

        Thread tA=new Thread(()->{
            int hit=0;
            int r=0;
            while(true){
                try{
                    r=rand.nextInt(1000);
                    Thread.sleep(r);
                    if(x>500){
                        System.out.println("Il valore di hit è: "+hit+", Invece di x è: "+x);
                        break;
                    }else{
                        x++;
                        hit++;
                        System.out.println("tA incremente x e hit");
                    }

                    synchronized(lock){
                        lock.notify();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        
        Thread tB=new Thread(()->{
            int hit=0;
            int r=0;
            while(true){
                try{
                    r=rand.nextInt(1000);
                    Thread.sleep(r);
                    if(x>500){
                        System.out.println("Il valore di hit è: "+hit+", Invece di x è: "+x);
                        break;
                    }else{
                        x++;
                        hit++;
                        System.out.println("tB incremente x e hit");
                    }

                    synchronized(lock){
                        lock.notify();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        tA.start();
        tB.start();

        try{
            tA.join();
            tB.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}