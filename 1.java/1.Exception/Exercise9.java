/*
9. Write a Java program to create a method that takes a string as input and throws an
exception if the string does not contain vowels.
Sample Output:
The string 'rhythm' does not contain any vowels.
The string 'hello' contains vowels.
*/
public class Exercise9 {
    static void checkVowels(String str) throws Exception {
        if (!str.toLowerCase().matches(".*[aeiou].*")) {
            throw new Exception("The string '" + str + "' does not contain any vowels.");
        }
        System.out.println("The string '" + str + "' contains vowels.");
    }

    public static void main(String[] args) {
        String[] tests = {"rhythm", "hello"};
        for (String s : tests) {
            try {
                checkVowels(s);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
