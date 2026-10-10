/*
5. Now repeat the above example to print the area of 15 squares.
Hint-Use array of objects
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

public class Exercise5 {
    public static void main(String[] args) {
        Square[] squares = new Square[15];      // array of objects

        for (int i = 0; i < squares.length; i++) {
            squares[i] = new Square(i + 1);     // sides 1, 2, ..., 15
        }

        for (int i = 0; i < squares.length; i++) {
            System.out.print("Square " + (i + 1) + " (side " + (i + 1) + "): ");
            squares[i].printArea();
        }
    }
}
