/*
4. Create a class named 'Rectangle' with two data members 'length' and 'breadth' and two
methods to print the area and perimeter of the rectangle respectively. Its constructor having
parameters for length and breadth is used to initialize length and breadth of the rectangle.
Let class 'Square' inherit the 'Rectangle' class with its constructor having a parameter for
its side (suppose s) calling the constructor of its parent class as 'super(s,s)'. Print the area
and perimeter of a rectangle and a square.
*/
class Rectangle {
    private double length;
    private double breadth;

    public Rectangle(double length, double breadth) {
        this.length = length;
        this.breadth = breadth;
    }

    public void printArea() {
        System.out.println("Area = " + (length * breadth));
    }

    public void printPerimeter() {
        System.out.println("Perimeter = " + (2 * (length + breadth)));
    }
}

class Square extends Rectangle {
    public Square(double s) {
        super(s, s);
    }
}

public class Exercise4 {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(8, 5);
        System.out.println("Rectangle (8 x 5):");
        rectangle.printArea();
        rectangle.printPerimeter();

        Square square = new Square(6);
        System.out.println("Square (side 6):");
        square.printArea();
        square.printPerimeter();
    }
}
