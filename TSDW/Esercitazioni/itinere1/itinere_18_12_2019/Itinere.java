import java.util.Random;

public class Itinere 
{
    static Random rand = new Random();
    private static int x = rand.nextInt(11); ;
    
    public static void main(String[] args) 
    {
        Thread t1 = new Thread(() -> {
            Random rand1 = new Random();
            while (true) 
            {
                try 
                {
                    Thread.sleep(100);
                    int m = rand1.nextInt(11);
                    if (x == -1) 
                        break;
                    else 
                    {
                        if (m == x) 
                        {
                            System.out.println("RISPOSTA CORRETTA");
                            x = -1; 
                            break;
                        } 
                        else if (Math.abs(m - x) > 5) 
                            System.out.println("risposta MOLTO sbagliata");
                        else 
                            System.out.println("risposta sbagliata");
                    }
                } 
                catch (InterruptedException e) 
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
                    synchronized (t1) 
                    {
                        t1.notify(); 
                    }
                    if (x == -1) 
                        break;
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
