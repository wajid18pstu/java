/*
13. (Constructor Failure) Write a program that shows a constructor passing information
about constructor failure to an exception handler. Define class SomeClass, which throws
an Exception in the constructor. Your program should try to create an object of type
SomeClass and catch the exception that’s thrown from the constructor.
*/
class SomeClass {
    public SomeClass() throws Exception {
        throw new Exception("Exception thrown in the SomeClass constructor");
    }
}

public class Exercise13 {
    public static void main(String[] args) {
        try {
            new SomeClass();
            System.out.println("Object created successfully");
        } catch (Exception e) {
            System.out.println("Constructor failure caught: " + e.getMessage());
        }
    }
}
