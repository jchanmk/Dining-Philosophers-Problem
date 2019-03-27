import java.lang.*;
import java.util.concurrent.Semaphore;

public class DiningPhilosophers {
    public static void main(String[] args) {
        Object fork1 = new Object();
        Object fork2 = new Object();
        Object fork3 = new Object();
        Object fork4 = new Object();
        Object fork5 = new Object();

        Philosopher Philosopher1 = new Philosopher("Philosopher 1", fork1, fork2);
        Philosopher Philosopher2 = new Philosopher("Philosopher 2", fork2, fork3);
        Philosopher Philosopher3 = new Philosopher("Philosopher 3", fork3, fork4);
        Philosopher Philosopher4 = new Philosopher("Philosopher 4", fork4, fork5);
        Philosopher Philosopher5 = new Philosopher("Philosopher 5", fork5, fork1);

        Philosopher1.start();
        Philosopher2.start();
        Philosopher3.start();
        Philosopher4.start();
        Philosopher5.start();
        try {
            // Philosopher1.join();
            Philosopher2.join();
            Philosopher3.join();
            Philosopher4.join();
            Philosopher5.join();
        } catch (InterruptedException ie) {
        }
    }
}

class Philosopher extends Thread {
    public Object forkLeft;
    public Object forkRight;

    public String name;
    public static Semaphore sem = new Semaphore(1);

    public Philosopher(String s, Object fork1, Object fork2) {
        this.name = s;
        this.forkLeft = fork1;
        this.forkRight = fork2;
    }

    public void run() {

        try {
            sem.acquire();                  //solves the deadlock problem

        } catch (InterruptedException e) {
        }

        synchronized (forkLeft) {
            //   System.out.println(name + " grabbing left chopstick");

            synchronized (forkRight) {
                //     System.out.println(name + " grabbing right chopstick");

                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                }
                System.out.println(name + " finished eating");
            }
        }
        sem.release();
    }
}
