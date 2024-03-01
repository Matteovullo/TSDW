import java.util.Random;

public class Prova {
    volatile static int n = 0;
    private static Random rand = new Random();
    static Object lock = new Object();

    public static void main(String args[]) {

        Thread tO = new Thread(() -> {
            int cicli = 0;

            while (cicli<=1000) {
                try {
                    Thread.sleep(2000);
                    int tmp;
                    do {
                        tmp = rand.nextInt(1000);
                    } while (tmp % 2 == 0);

                    cicli++;

                    synchronized (lock) {
                        n += tmp;

                        if (cicli >= 10 && n % 2 != 0) {
                            break;
                        }

                        System.out.println("tO modifica n: "+n);

                        lock.notify(); // Notifica tO
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread tE = new Thread(() -> {
            int cicli = 0;

            while (cicli<=1000) {
                try {
                    Thread.sleep(2000);
                    int tmp;
                    do {
                        tmp = rand.nextInt(1000);
                    } while (tmp % 2 != 0);

                    cicli++;

                    synchronized (lock) {
                        n += tmp;

                        if (cicli >= 10 && n % 2 != 0) {
                            break;
                        }

                        System.out.println("tE modifica n: "+n);

                        lock.notify(); // Notifica tO
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        tO.start();
        tE.start();

        try {
            tO.join();
            tE.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
