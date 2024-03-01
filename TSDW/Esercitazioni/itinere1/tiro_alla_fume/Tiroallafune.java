/*
 * Scrivere un programma in java che simuli un incontro di “tiro alla fune” tra due 2 thread “giocatori” tp[0], tp[1].

    E’ data una variabile globale intera posizione (con valore iniziale 0) condivisa da tutti i thread. 
    Sono date inoltre due variabili globali intere, vittorie_tp0 e vittorie_tp1.

    Ogni thread giocatore esegue un ciclo in cui:
    - genera un intero casuale recupero compreso tra 0 e 3
    - genera un intero casuale forza compreso tra 0 e 5
    - attende recupero secondi 

    - se tp[0]:
        - se posizione >= 10 riconosce la vittoria di tp[1] e: 
            - incrementa vittorie_tp1
            - setta posizione = 0
            - sveglia tp[1]
        -altrimenti: 
            - decrementa posizione di forza
            - se posizione <= -10 ha vinto, e si mette in attesa di tp[1]
            
    - se tp[1]:
        - se posizione <= -10 riconosce la vittoria di tp[0] e: 
            - incrementa vittorie_tp0
            - setta posizione = 0
            - sveglia tp[0]
        - altrimenti:
            - incrementa posizione di forza
            - se posizione >= 10 ha vinto, e si mette in attesa di tp[0]

    (Opzionale) quando uno dei giocatori ha raggiunto 10 vittorie interrompere il gioco, 
    entrambi i giocatori tp[0], tp[1] devono aver terminato la loro esecuzione, 
    e la funzione main() se ne deve accorgere scrivendo 
    sullo standard output il giocatore che ha totalizzato più vittorie.
 */

import java.util.Random;

public class Tiroallafune {
    static volatile int posizione = 0;
    static int vittorie_tp0 = 0;
    static int vittorie_tp1 = 0;
    static Object lock = new Object();
    static Random rand = new Random();

    public static void main(String args[]) {
        Thread td0 = new Thread(() -> {
            int recupero = 0;
            int forza = 0;
            while (true) {
                recupero = rand.nextInt(3);
                forza = rand.nextInt(5);

                try {
                    Thread.sleep(recupero * 1000);

                    synchronized (lock) {
                        if (posizione >= 10) {
                            System.out.println("vittorie tp1 = " + vittorie_tp1);
                            vittorie_tp1++;
                            posizione = 0;
                            lock.notify();
                        } else {
                            posizione -= forza;
                            if (posizione <= -10) {
                            vittorie_tp0++;
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread td1 = new Thread(() -> {
            int recupero = 0;
            int forza = 0;
            while (true) {
                recupero = rand.nextInt(3);
                forza = rand.nextInt(5);
                
                try {
                    Thread.sleep(recupero * 1000);
                    
                    if (vittorie_tp0 == 10) {
                        System.out.println("tp0 ha vinto!");
                        break;
                    } else if(vittorie_tp1 == 10){
                        System.out.println("tp1 ha vinto!");
                        break;
                    }

                    synchronized (lock) {
                        if (posizione <= -10) {
                            System.out.println("vittorie tp0 = " + vittorie_tp0);
                            vittorie_tp0++;
                            posizione = 0;
                            lock.notify();
                        } else {
                            posizione += forza;
                            if (posizione >= 10) {
                            vittorie_tp1++;
                                try {
                                    lock.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        td0.start();
        td1.start();

        try {
            td0.join();
            td1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
