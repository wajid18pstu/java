import java.io.IOException;

/*
11. (Catching Exceptions Using Class Exception) Write a program that demonstrates how
various exceptions are caught with catch (Exception exception ) This time, define classes
ExceptionA (which inherits from class Exception) and ExceptionB (which inherits from
class ExceptionA). In your program, create try blocks that throw exceptions of types
ExceptionA, ExceptionB, NullPointerException and IOException. All exceptions should
be caught with catch blocks specifying type Exception.
*/
public class Exercise11 {
    static class ExceptionA extends Exception {
        public ExceptionA(String message) { super(message); }
    }

    static class ExceptionB extends ExceptionA {
        public ExceptionB(String message) { super(message); }
    }

    public static void main(String[] args) {
        try {
            throw new ExceptionA("ExceptionA thrown");
        } catch (Exception exception) {
            System.out.println("Caught: " + exception);
        }

        try {
            throw new ExceptionB("ExceptionB thrown");
        } catch (Exception exception) {
            System.out.println("Caught: " + exception);
        }

        try {
            throw new NullPointerException("NullPointerException thrown");
        } catch (Exception exception) {
            System.out.println("Caught: " + exception);
        }

        try {
            throw new IOException("IOException thrown");
        } catch (Exception exception) {
            System.out.println("Caught: " + exception);
        }
    }
}
