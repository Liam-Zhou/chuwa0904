package Question17;

public class ShapeMain {
    public static void main(String[] args) {
        // Create a ShapeFactory object
        ShapeFactory shapeFactory = new ShapeFactory();

        // Get a Circle object and call its draw method
        Shape shape1 = shapeFactory.getShape("CIRCLE");
        shape1.draw();  // Output: Drawing a Circle.

        // Get a Rectangle object and call its draw method
        Shape shape2 = shapeFactory.getShape("RECTANGLE");
        shape2.draw();  // Output: Drawing a Rectangle.

    }
}
