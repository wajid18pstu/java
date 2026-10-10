import java.util.InputMismatchException;
import java.util.Scanner;

/*
2. Create a program to ask the user for a real number and display its square root. Errors
must be trapped using "try..catch".
Sample Output:
Enter a real number: 25
The square root of 25.0 is 5.0
Enter a real number: -9
Cannot compute the square root of a negative number.
Enter a real number: abc
Error: Invalid input. Please enter a valid real number.
*/
public class Exercise2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter a real number: ");
            double num = sc.nextDouble();
            if (num < 0) {
                throw new ArithmeticException("negative");
            }
            System.out.println("The square root of " + num + " is " + Math.sqrt(num));
        } catch (ArithmeticException e) {
            System.out.println("Cannot compute the square root of a negative number.");
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input. Please enter a valid real number.");
        } finally {
            sc.close();
        }
    }
}
