import java.util.concurrent.locks.ReentrantLock;

public class es {
    static int x=0;
    static ReentrantLock lock=new ReentrantLock();
    public static void main(String[] args){
        Thread A=new Thread(new Myrunnable("A"));
        Thread B=new Thread(new Myrunnable("B"));

        A.start();
        B.start();

        try{
            A.join();
            B.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    static public class Myrunnable implements Runnable{
        String name;

        Myrunnable(String th){
            this.name=th;
        }

        public void run(){
            int cnt=0;
            while(true){
                if(x>300){
                    System.out.println("Valore di x ha superato i 300, cnt = "+cnt);
                    break;
                }

                lock.lock();
                try{
                    x++;
                    cnt++;
                    System.out.println("Thread "+name+ ", incrementa, x = "+x+", cnt = "+cnt);
                }finally{
                    lock.unlock();
                }

            }
        }
    }
}
