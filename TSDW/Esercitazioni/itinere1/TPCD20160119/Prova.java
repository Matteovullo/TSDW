import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Prova{
    static int x=0;
    static final ReentrantLock lock=new ReentrantLock();

    public static void main(String args[]){
        Thread ta=new Thread(new MyRunnable("A"));
        Thread tb=new Thread(new MyRunnable("B"));

        ta.start();
        tb.start();

        try{
            ta.join();
            tb.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    static class MyRunnable implements Runnable{
        int cnt=0;
        private Random rand=new Random();
        String nome;

        MyRunnable(String _nome){
            nome=_nome;
        }

        public void run(){
            while(true){
                try{
                    int tmp=rand.nextInt(2000);
                    Thread.sleep(tmp);

                    if(x>300){
                        System.out.println("Thread "+nome+" termina, cnt="+cnt);
                        break;
                    }

                    lock.lock();
                    try{
                        x++;
                        cnt++;
                        System.out.println("Thread "+nome+", x="+x+", cnt="+cnt);
                    }finally{
                        lock.unlock();
                    }

                    if(x>300){
                        System.out.println("Thread "+nome+" termina, cnt="+cnt);
                        break;
                    }
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}