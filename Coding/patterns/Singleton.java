package patterns;
interface ShapeSingleton {
    void draw();
}
class CircleSingleton implements ShapeSingleton {
    @Override
    public void draw() {
        System.out.println("Drawing a Circle");
    }
}

class RectangleSingleton implements ShapeSingleton {
    @Override
    public void draw() {
        System.out.println("Drawing a Rectangle");
    }
}

class SingletonFactory {
    private static volatile SingletonFactory instance;

    private SingletonFactory() {
    }

    public static SingletonFactory getInstance() {
        if (instance == null) {
            synchronized (SingletonFactory.class) {
                if (instance == null) {
                    instance = new SingletonFactory();
                }
            }
        }
        return instance;
    }

    //Factory method
    public ShapeSingleton getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new CircleSingleton();
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new RectangleSingleton();
        }
        return null;
    }
}