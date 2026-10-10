/*
7. Write an inheritance hierarchy for classes Quadrilateral, Trapezoid, Parallelogram, Rectangle and
Square. Use Quadrilateral as the superclass of the hierarchy. Create and use a Point class to
represent the points in each shape. Make the hierarchy as deep (i.e., as many levels) as
possible. Specify the instance variables and methods for each class. The private instance
variables of Quadrilateral should be the x-y coordinate pairs for the four endpoints of the
Quadrilateral. Write a program that instantiates objects of your classes and outputs each
object’s area (except Quadrilateral).
*/

// Represents a point with x-y coordinates
class Point {
    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public double getY() { return y; }

    public double distanceTo(Point other) {
        double dx = x - other.x;
        double dy = y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

// Level 1: four endpoints given in order around the shape (p1 -> p2 -> p3 -> p4)
class Quadrilateral {
    private final Point p1, p2, p3, p4;

    public Quadrilateral(Point p1, Point p2, Point p3, Point p4) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
    }

    public Point getP1() { return p1; }
    public Point getP2() { return p2; }
    public Point getP3() { return p3; }
    public Point getP4() { return p4; }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " with points " + p1 + ", " + p2 + ", " + p3 + ", " + p4;
    }
}

// Level 2: sides p1p2 and p4p3 are parallel (the bases)
class Trapezoid extends Quadrilateral {
    public Trapezoid(Point p1, Point p2, Point p3, Point p4) {
        super(p1, p2, p3, p4);
    }

    // Perpendicular distance from p3 to the line through p1 and p2
    public double getHeight() {
        Point a = getP1(), b = getP2(), c = getP3();
        double cross = (b.getX() - a.getX()) * (c.getY() - a.getY())
                     - (b.getY() - a.getY()) * (c.getX() - a.getX());
        return Math.abs(cross) / a.distanceTo(b);
    }

    public double getArea() {
        double base1 = getP1().distanceTo(getP2());
        double base2 = getP4().distanceTo(getP3());
        return 0.5 * (base1 + base2) * getHeight();
    }
}

// Level 3: both pairs of opposite sides are parallel
class Parallelogram extends Trapezoid {
    public Parallelogram(Point p1, Point p2, Point p3, Point p4) {
        super(p1, p2, p3, p4);
    }

    @Override
    public double getArea() {
        return getP1().distanceTo(getP2()) * getHeight();   // base * height
    }
}

// Level 4: a parallelogram with right angles
class Rectangle extends Parallelogram {
    public Rectangle(Point p1, Point p2, Point p3, Point p4) {
        super(p1, p2, p3, p4);
    }

    @Override
    public double getArea() {
        return getP1().distanceTo(getP2()) * getP2().distanceTo(getP3());   // width * height
    }
}

// Level 5: a rectangle with all sides equal
class Square extends Rectangle {
    private final double side;

    public Square(double x, double y, double side) {
        super(new Point(x, y), new Point(x + side, y),
              new Point(x + side, y + side), new Point(x, y + side));
        this.side = side;
    }

    @Override
    public double getArea() {
        return side * side;
    }
}

public class Exercise7 {
    public static void main(String[] args) {
        Quadrilateral quad = new Quadrilateral(
                new Point(0, 0), new Point(6, 1), new Point(5, 5), new Point(1, 4));

        Trapezoid trapezoid = new Trapezoid(
                new Point(0, 0), new Point(10, 0), new Point(8, 4), new Point(2, 4));

        Parallelogram parallelogram = new Parallelogram(
                new Point(0, 0), new Point(6, 0), new Point(8, 3), new Point(2, 3));

        Rectangle rectangle = new Rectangle(
                new Point(0, 0), new Point(5, 0), new Point(5, 3), new Point(0, 3));

        Square square = new Square(0, 0, 4);

        // Area is not output for the Quadrilateral
        System.out.println(quad);
        System.out.println();

        System.out.println(trapezoid);
        System.out.printf("Area of Trapezoid = %.2f%n%n", trapezoid.getArea());

        System.out.println(parallelogram);
        System.out.printf("Area of Parallelogram = %.2f%n%n", parallelogram.getArea());

        System.out.println(rectangle);
        System.out.printf("Area of Rectangle = %.2f%n%n", rectangle.getArea());

        System.out.println(square);
        System.out.printf("Area of Square = %.2f%n", square.getArea());
    }
}
