import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
8. Write a Java program that reads a list of integers from the user and throws an exception if
any numbers are duplicates.
Sample Output:
Enter integers (enter a non-integer to stop):
Enter a number: 10
Enter a number: 20
Enter a number: 30
Enter a number: 20
Duplicate number found: 20
*/
class DuplicateNumberException extends Exception {
    public DuplicateNumberException(String message) {
        super(message);
    }
}

public class Exercise8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Set<Integer> numbers = new HashSet<>();
        System.out.println("Enter integers (enter a non-integer to stop):");
        try {
            while (true) {
                System.out.print("Enter a number: ");
                if (!sc.hasNextInt()) {
                    break;
                }
                int n = sc.nextInt();
                if (!numbers.add(n)) {
                    throw new DuplicateNumberException("Duplicate number found: " + n);
                }
            }
            System.out.println("No duplicates found.");
        } catch (DuplicateNumberException e) {
            System.out.println(e.getMessage());
        }
    }
}
