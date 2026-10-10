/*
3. Write a Java program that throws an exception and catch it using a try-catch block.
Sample Output:
Exception caught: This is a custom exception message!
*/
public class Exercise3 {
    public static void main(String[] args) {
        try {
            throw new Exception("This is a custom exception message!");
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }
}
