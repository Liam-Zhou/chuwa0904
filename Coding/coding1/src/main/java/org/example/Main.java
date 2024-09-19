package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(2, 10);  // Parking lot with 2 levels, 10 spots each

        Vehicle car = new Car("ABC123");
        Vehicle motorcycle = new Motorcycle("XYZ789");
        Vehicle bus = new Bus("BUS456");

        // Park vehicles
        System.out.println("Parking car: " + parkingLot.parkVehicle(car));  // True if parked
        System.out.println("Parking motorcycle: " + parkingLot.parkVehicle(motorcycle));  // True if parked
        System.out.println("Parking bus: " + parkingLot.parkVehicle(bus));  // True if parked

        // Remove a vehicle
        parkingLot.removeVehicle(0, 2);  // Removes the vehicle from level 0, spot 2
    }
}