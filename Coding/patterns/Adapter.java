package patterns;

interface ShapeAdapter {
    void draw();
    void resize();
}
class LegacyCircle {
    public void drawCircle() {
        System.out.println("Drawing a circle");
    }

    public void resizeCircle() {
        System.out.println("Resizing a circle");
    }
}
class LegacyRectangle {
    public void drawRectangle() {
        System.out.println("Drawing a rectangle");
    }

    public void resizeRectangle() {
        System.out.println("Resizing a rectangle");
    }
}

class CircleAdapter implements ShapeAdapter {
    private final LegacyCircle legacyCircle;

    public CircleAdapter(LegacyCircle legacyCircle) {
        this.legacyCircle = legacyCircle;
    }

    @Override
    public void draw() {
        legacyCircle.drawCircle();
    }

    @Override
    public void resize() {
        legacyCircle.resizeCircle();
    }
}

class RectangleAdapter implements ShapeAdapter {
    private final LegacyRectangle legacyRectangle;

    public RectangleAdapter(LegacyRectangle legacyRectangle) {
        this.legacyRectangle = legacyRectangle;
    }

    @Override
    public void draw() {
        legacyRectangle.drawRectangle();  // Adapting to Shape's draw() method
    }

    @Override
    public void resize() {
        legacyRectangle.resizeRectangle();  // Adapting to Shape's resize() method
    }
}
class AdapterPatternDemo {
    public static void main(String[] args) {
        LegacyRectangle legacyRectangle = new LegacyRectangle();
        LegacyCircle legacyCircle = new LegacyCircle();

        ShapeAdapter rectangleAdapter = new RectangleAdapter(legacyRectangle);
        ShapeAdapter circleAdapter = new CircleAdapter(legacyCircle);

        rectangleAdapter.draw();
        rectangleAdapter.resize();

        circleAdapter.draw();
        circleAdapter.resize();
    }
}