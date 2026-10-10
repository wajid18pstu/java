/*
Write a Java program to create a base class Shape with methods draw() and calculateArea(). Create
two subclasses Circle and Cylinder. Override the draw() method in each subclass to draw the
respective shape. In addition, override the calculateArea() method in the Cylinder subclass to
calculate and return the total surface area of the cylinder.
*/
class Shape {
    public void draw() {
        System.out.println("Drawing a shape");
    }

    public double calculateArea() {
        return 0;               // a general shape has no specific area
    }
}

class Circle extends Shape {
    protected double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a Circle:");
        System.out.println("    ***    ");
        System.out.println("  *     *  ");
        System.out.println(" *       * ");
        System.out.println("  *     *  ");
        System.out.println("    ***    ");
    }

    // Area of a circle = pi * r^2
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

class Cylinder extends Circle {
    private double height;

    public Cylinder(double radius, double height) {
        super(radius);          // the circular base uses the Circle's radius
        this.height = height;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a Cylinder:");
        System.out.println("   _______   ");
        System.out.println("  /       \\  ");
        System.out.println("  \\_______/  ");
        System.out.println("  |       |  ");
        System.out.println("  |       |  ");
        System.out.println("  \\_______/  ");
    }

    // Total surface area of a cylinder = 2 * pi * r * (r + h)
    @Override
    public double calculateArea() {
        return 2 * Math.PI * radius * (radius + height);
    }
}

public class ShapeDrawing {
    public static void main(String[] args) {
        // Shape references holding subclass objects, so the overridden methods are used
        Shape[] shapes = {
            new Shape(),
            new Circle(5),
            new Cylinder(5, 10)
        };

        for (Shape shape : shapes) {
            shape.draw();
            System.out.printf("Area: %.2f%n%n", shape.calculateArea());
        }
    }
}
