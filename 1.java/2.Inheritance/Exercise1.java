/*
1. Create a class with a method that prints "This is parent class" and its subclass with another
method that prints "This is child class". Now, create an object for each of the class and call
1 - method of parent class by object of parent class
2 - method of child class by object of child class
3 - method of parent class by object of child class
*/
class Parent {
    public void printParent() {
        System.out.println("This is parent class");
    }
}

class Child extends Parent {
    public void printChild() {
        System.out.println("This is child class");
    }
}

public class Exercise1 {
    public static void main(String[] args) {
        Parent parent = new Parent();
        Child child = new Child();

        parent.printParent();   // 1 - parent method by parent object
        child.printChild();     // 2 - child method by child object
        child.printParent();    // 3 - parent method by child object (inherited)
    }
}
