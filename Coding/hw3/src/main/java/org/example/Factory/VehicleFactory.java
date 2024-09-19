package org.example.Factory;

// Factory class to create instances of Vehicle
public class VehicleFactory {
    // Method to create Vehicle objects
    public Vehicle getVehicle(String vehicleType) {
        if (vehicleType == null) {
            return null;
        }
        if (vehicleType.equalsIgnoreCase("Car")) {
            return new Car();  // Returns Car object
        } else if (vehicleType.equalsIgnoreCase("Bike")) {
            return new Bike();  // Returns Bike object
        }
        return null;
    }
}
