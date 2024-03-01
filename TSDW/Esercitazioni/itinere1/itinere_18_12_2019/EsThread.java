import java.util.Random;

public class EsThread 
{
    static Random rand = new Random();
    volatile static int x = rand.nextInt(11); // volatile perchè condivisa tra diversi thread
    static Object lock = new Object();

    public static void main(String[] args) 
    {
        Thread t1 = new Thread(() -> {
            int m = 0;
            while (true)
            {
                try 
                {
                    Thread.sleep(100);
                    m = rand.nextInt(11);
                    
                    if(x == -1)
                        break;

                    else 
                    {
                        if(m == x)
                        {
                            System.out.println("Risposta corretta");
                            x = -1;
                            break;
                        }

                        if (Math.abs(m - x) > 5) 
                        {
                            System.out.println("Risposta molto sbagliata");
                            synchronized (lock) 
                            {
                                try 
                                {
                                    lock.wait();
                                } 
                                catch (InterruptedException e) 
                                {
                                    e.printStackTrace();
                                }
                            }
                        }

                        else 
                            System.out.println("Risposta sbagliata");
                    }
                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
            }

        });

        Thread t2 = new Thread(() -> {
            while (true) 
            {
                try 
                {
                    Thread.sleep(300);
                    synchronized (lock) 
                    {
                        lock.notify(); 
                    }

                    if (x == -1) 
                        break;
                    else
                        continue;
                } 
                catch (InterruptedException e) 
                {
                    e.printStackTrace();
                }
            }

        });

        t1.start();

        t2.start();
    }
}