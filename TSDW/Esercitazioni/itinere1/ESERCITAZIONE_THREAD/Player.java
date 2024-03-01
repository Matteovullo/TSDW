import java.util.*;

public class Player extends Thread {
    Random rand = new Random();
    int t = 0;
    int posizione = 0;
    Game g;

    public Player(Game g) {
        this.g = g;
    }

    public void setT(int turno) {
        t = turno;
    }

    public void run() {
        while (true) {
            if (g.turno == -1) {
                break;
            }

            if (g.turno == t) {
                posizione += rand.nextInt(6);
                System.out.println("Thread " + t + ", posizione: " + posizione);

                if (posizione > 100) {
                    System.out.println("100 superato");
                    g.n();
                    g.set(-1);
                    break;
                }

                g.set(1 - t);
                g.n();

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    g.w();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
