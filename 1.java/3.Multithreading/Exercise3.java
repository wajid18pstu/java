/*
3. Write a program to print "Good morning" and "Welcome" continuously on the screen in
Java using threads.
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
        }
    }
}

public class Exercise3 {
    public static void main(String[] args) {
        GoodMorningThread t1 = new GoodMorningThread();
        WelcomeThread t2 = new WelcomeThread();

        t1.start();
        t2.start();
        // Both threads run forever. Press Ctrl+C to stop the program.
    }
}
