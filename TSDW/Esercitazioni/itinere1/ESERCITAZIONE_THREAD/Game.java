public class Game {
    int turno = 0;

    public synchronized void set(int turno) {
        this.turno = turno;
    }

    public synchronized void n() {
        notify();
    }

    public synchronized void w() throws InterruptedException {
        wait();
    }
}

