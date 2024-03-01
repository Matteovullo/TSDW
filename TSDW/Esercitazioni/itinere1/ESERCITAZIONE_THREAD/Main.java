public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Player t0 = new Player(game);
        Player t1 = new Player(game);

        t0.setT(0);
        t1.setT(1);

        t0.start();
        t1.start();

        try {
            t0.join();
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

