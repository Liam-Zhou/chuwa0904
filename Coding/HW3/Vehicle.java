abstract class Vehicle {
    protected String licensePlate;
    protected int size;

    public Vehicle(String licensePlate, int size) {
        this.licensePlate = licensePlate;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public abstract String getType();
}

class Car extends Vehicle {
    public Car(String licensePlate) {
        super(licensePlate, 1); // Cars take 1 unit space
    }

    @Override
    public String getType() {
        return "Car";
    }
}

class Motorcycle extends Vehicle {
    public Motorcycle(String licensePlate) {
        super(licensePlate, 1); // Motorcycles take 1 unit space
    }

    @Override
    public String getType() {
        return "Motorcycle";
    }
}

class Truck extends Vehicle {
    public Truck(String licensePlate) {
        super(licensePlate, 3); // Trucks take 3 unit spaces
    }

    @Override
    public String getType() {
        return "Truck";
    }
}
