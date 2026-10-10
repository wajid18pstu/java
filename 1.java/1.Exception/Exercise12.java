/*
12. (Order of catch Blocks) Write a program that shows that the order of catch blocks is
important. If you try to catch a superclass exception type before a subclass type, the
compiler should generate errors.
*/
public class Exercise12 {
    public static void main(String[] args) {
        
         // WRONG ORDER - uncomment this block and the compiler reports:
         //   "exception ArithmeticException has already been caught"
         
//          try {
 //             int result = 10 / 0;
   //       } catch (Exception e) {                 // superclass first
     //         System.out.println("General exception");
       //   } catch (ArithmeticException e) {       // subclass after -> compile error (unreachable)
         //     System.out.println("Arithmetic exception");
         //}
         

        // CORRECT ORDER - subclass first, then superclass
        try {
            int result = 10 / 0;
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic exception caught: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("General exception caught: " + e.getMessage());
        }
    }
}
