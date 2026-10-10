/*
15. (Catching Exceptions Using Outer Scopes) Write a program showing that a method with
its own try block does not have to catch every possible error generated within the try.
Some exceptions can slip through to, and be handled in, other scopes.
*/
public class Exercise15 {
    static void method() {
        try {
            int[] arr = new int[3];
            arr[5] = 10;          // throws ArrayIndexOutOfBoundsException
        } catch (ArithmeticException e) {
            // Only ArithmeticException is handled here
            System.out.println("method caught ArithmeticException: " + e.getMessage());
        }
        System.out.println("This line in method() is never reached.");
    }

    public static void main(String[] args) {
        try {
            method();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("main caught the exception that slipped through: " + e);
        }
        System.out.println("Program continues normally.");
    }
}
