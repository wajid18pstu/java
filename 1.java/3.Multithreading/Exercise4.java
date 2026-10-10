/*
4. Add a step method in the welcome thread of question 3 to delay its execution for 200ms.
*/
class GoodMorningThread extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println("Good morning");
        }
    }
}

class WelcomeThread extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println("Welcome");
            try {
                Thread.sleep(200);   // delay the Welcome thread by 200 ms
            } catch (InterruptedException e) {
                System.out.println("Welcome thread interrupted");
                return;
            }
        }
    }
}

public class Exercise4 {
    public static void main(String[] args) {
        GoodMorningThread t1 = new GoodMorningThread();
        WelcomeThread t2 = new WelcomeThread();

        t1.start();
        t2.start();
        // Both threads run forever. Press Ctrl+C to stop the program.
    }
}
