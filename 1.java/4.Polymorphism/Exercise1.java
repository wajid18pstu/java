/*
1. Write a console based program to implement polymorphism using inheritance. Consider the
example of Shape as base class with method show(). And then a child class Circle and Rectangle
which inherit the base class Shape and override its method show(). Add one more Method with the
name of getInfo(). This method would display the class name in which it is implemented. Do not
override this method. When you will call the method getInfo() with child object it would still show
the name of the base class, which implies that method has been directly inherited and was not
overridden.
*/
class Shape {
    public void show() {
        System.out.println("This is a Shape");
    }

    // Not overridden in the child classes, so it always reports the base class name
    public void getInfo() {
        System.out.println("getInfo() is implemented in class: " + Shape.class.getSimpleName());
    }
}

class Circle extends Shape {
    @Override
    public void show() {
        System.out.println("This is a Circle");
    }
}

class Rectangle extends Shape {
    @Override
    public void show() {
        System.out.println("This is a Rectangle");
    }
}

public class Exercise1 {
    public static void main(String[] args) {
        Shape s1 = new Circle();       // polymorphic references
        Shape s2 = new Rectangle();

        s1.show();      // Circle's show()
        s1.getInfo();   // still shows Shape

        s2.show();      // Rectangle's show()
        s2.getInfo();   // still shows Shape
    }
}
