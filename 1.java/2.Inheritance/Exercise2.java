/*
2. In the above example, declare the method of the parent class as private and then repeat
the first two operations (You will get error in the third).
*/
// The parent class is the public class here and holds main(). A private method can only be
// called from inside its own class, so operation 1 works from main() in the same class.
public class Exercise2 {
    private void printParent() {
        System.out.println("This is parent class");
    }

    public static void main(String[] args) {
        Exercise2 parent = new Exercise2();
        Child child = new Child();

        parent.printParent();   // 1 - works: called inside the class that declares it
        child.printChild();     // 2 - works

        // 3 - ERROR: a private method is not inherited by the child class.
        // Uncomment the next line and the compiler reports:
        //   "cannot find symbol: method printParent()"
        // child.printParent();
    }
}

class Child extends Exercise2 {
    public void printChild() {
        System.out.println("This is child class");
    }
}
