/*
4. Write a Java program to create a method that takes an integer as a parameter and throws
an exception if the number is odd.
Sample Output:
The number 7 is odd!
The number 8 is even.
*/
public class Exercise4 {
    static void checkEven(int number) throws Exception {
        if (number % 2 != 0) {
            throw new Exception("The number " + number + " is odd!");
        }
        System.out.println("The number " + number + " is even.");
    }

    public static void main(String[] args) {
        try {
            checkEven(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            checkEven(8);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
