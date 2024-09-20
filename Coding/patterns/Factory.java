package patterns;
// Product Interface
interface ShapesFactory {
    void display();
}


class Circle implements ShapesFactory {
    @Override
    public void display() {
        System.out.println("Drawing a Circle.");
    }
}

class Rectangle implements ShapesFactory {
    @Override
    public void display() {
        System.out.println("Drawing a Rectangle.");
    }
}

// Factory Interface
interface Factory {
    ShapesFactory factoryMethod();
}


class CircleFactory implements Factory {
    @Override
    public ShapesFactory factoryMethod() {
        return new Circle();
    }
}

class RectangleFactory implements Factory {
    @Override
    public ShapesFactory factoryMethod() {
        return new Rectangle();
    }
}

// Client Code
class FactoryMethodExample {
    public static void main(String[] args) {
        Factory factoryA = new CircleFactory();
        ShapesFactory circle = factoryA.factoryMethod();
        circle.display();

        Factory factoryB = new RectangleFactory();
        ShapesFactory rectangle = factoryB.factoryMethod();
        rectangle.display();
    }
}
