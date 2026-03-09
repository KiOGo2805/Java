import java.util.List;

abstract static class Shape {
    abstract double getArea();
}

static class Circle extends Shape {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    double getArea() {
        return Math.PI * radius * radius;
    }
}

static class Rectangle extends Shape {
    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    double getArea() {
        return width * height;
    }
}

static class AreaCalculator {

    static double calculateTotalArea(List<? extends Shape> shapes) {
        double totalArea = 0;

        for (Shape shape : shapes) {
            totalArea += shape.getArea();
        }

        return totalArea;
    }
}

void main() {
    List<Circle> circles = List.of(
            new Circle(5.0),
            new Circle(3.0)
    );

    List<Rectangle> rectangles = List.of(
            new Rectangle(4.0, 5.0),
            new Rectangle(10.0, 2.0)
    );

    double totalCircleArea = AreaCalculator.calculateTotalArea(circles);
    double totalRectangleArea = AreaCalculator.calculateTotalArea(rectangles);

    System.out.printf("Загальна площа кіл: %.2f\n", totalCircleArea);
    System.out.printf("Загальна площа прямокутників: %.2f\n", totalRectangleArea);
}