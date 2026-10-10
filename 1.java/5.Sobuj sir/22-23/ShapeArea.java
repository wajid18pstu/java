/*
We have to calculate the area of a rectangle, a square and a circle. Create an abstract class
'Shape' with three abstract methods namely 'RectangleArea' taking two parameters,
'SquareArea' and 'CircleArea' taking one parameter each. The parameters of 'RectangleArea'
are its length and breadth, that of 'SquareArea' is its side and that of 'CircleArea' is its radius.
Now create another class 'Area' containing all the three methods 'RectangleArea', 'SquareArea'
and 'CircleArea' for printing the area of rectangle, square and circle respectively. Create an
object of class 'Area' and call all the three methods. Repeat the process for 4 rectangles, 4
squares and 5 circles.
*/
abstract class Shape {
    public abstract void RectangleArea(double length, double breadth);

    public abstract void SquareArea(double side);

    public abstract void CircleArea(double radius);
}

class Area extends Shape {

    @Override
    public void RectangleArea(double length, double breadth) {
        System.out.printf("Area of rectangle (length %.1f, breadth %.1f) = %.2f%n",
                length, breadth, length * breadth);
    }

    @Override
    public void SquareArea(double side) {
        System.out.printf("Area of square (side %.1f) = %.2f%n", side, side * side);
    }

    @Override
    public void CircleArea(double radius) {
        System.out.printf("Area of circle (radius %.1f) = %.2f%n", radius, Math.PI * radius * radius);
    }
}

public class ShapeArea {
    public static void main(String[] args) {
        Area area = new Area();             // one object of class Area

        // Data for 4 rectangles, 4 squares and 5 circles
        double[][] rectangles = {{5, 3}, {8, 4}, {10, 6}, {7.5, 2}};   // {length, breadth}
        double[] squares = {2, 4, 6, 9};                                // sides
        double[] circles = {1, 2.5, 3, 5, 7};                           // radii

        System.out.println("----- Rectangles -----");
        for (int i = 0; i < rectangles.length; i++) {
            area.RectangleArea(rectangles[i][0], rectangles[i][1]);
        }

        System.out.println();
        System.out.println("----- Squares -----");
        for (int i = 0; i < squares.length; i++) {
            area.SquareArea(squares[i]);
        }

        System.out.println();
        System.out.println("----- Circles -----");
        for (int i = 0; i < circles.length; i++) {
            area.CircleArea(circles[i]);
        }
    }
}
