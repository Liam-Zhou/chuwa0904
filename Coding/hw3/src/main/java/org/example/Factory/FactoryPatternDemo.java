package org.example.Factory;

public class FactoryPatternDemo {
    public static void main(String[] args) {
        // Create the factory object
        VehicleFactory vehicleFactory = new VehicleFactory();

        // Get an object of Car and call its start method
        Vehicle vehicle1 = vehicleFactory.getVehicle("Car");
        vehicle1.start();  // Output: Car has started.

        // Get an object of Bike and call its start method
        Vehicle vehicle2 = vehicleFactory.getVehicle("Bike");
        vehicle2.start();  // Output: Bike has started.
    }
}
